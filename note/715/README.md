# [Range Module][title]

## Solution

TreeMap 有个好用的方法 subMap 获取到原 Map 限定区间的部分视图， 对这部分数据的操作会映射到源数据上。
这样的话，这个问题就好解决了

add 时获取到小于 left, right 的两个 entry,  如果左侧的 entry 的 value > left 则更新左边界 同理如果右侧的 entry 的 value > right 则更新右边界
然后通过 subMap 清理掉 （left, right） 区间的 entry.

针对 query 呢， 获取到小于 left 的 entry, 如果他的 value 比我大的话， 直接说明包含了

针对 remove, 稍稍复杂点， 因为可能存在删除区间后， 需要拆分区间的情况。
获取到小于 left, right 的两个 entry,  如果左侧的 entry 的 value > left 则追加一部分区间 （left_entry.key, left）
同理如果右侧的 entry 的 value > right 则追加一部分区间 （right, right_entry.key）
然后通过 subMap 清理掉 （left, right） 区间的 entry.

```kotlin
import java.util.*

class RangeModule {
    
    private var intervals = TreeMap<Int, Int>()
    
    fun addRange(s: Int, e: Int) { // s: start, e: end
        // find overlap ranges, calc merged range, clear overlapped ranges, insert merged range
        var s = s
        var e = e
        val L = intervals.floorEntry(s) // left possible overlap entry
        val R = intervals.floorEntry(e) // right possible overlap entry
        // recall null-check
        if (L != null && L.value >= s) s = L.key // update overlap start
        if (R != null && R.value > e) e = R.value // update overlap end
        intervals.subMap(s, e).clear() // clear all overlapped entries
        intervals[s] = e // save final merged entry
    }

    fun queryRange(s: Int, e: Int): Boolean {
        val L = intervals.floorEntry(s)
        return L != null && L.value >= e // if there exist a range: start <+ s, end >= e
    }

    fun removeRange(s: Int, e: Int) {
        val L = intervals.floorEntry(s) // left possible overlap entry
        val R = intervals.floorEntry(e) // right possible overlap entry
        if (L != null && L.value > s) intervals[L.key] = s // after removal, if anything left
        if (R != null && R.value > e) intervals[e] = R.value // after removal, if anything left
        intervals.subMap(s, e).clear() // removal
    }
}
 

```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/finding-mk-average/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
