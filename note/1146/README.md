# [Snapshot Array][title]

## Solution
看到这道题时，第一想法是存下每一个快照的全数据，但在数据量大时，内存会裂的。

看了题解是通过 TreeMap 的 floorKey 作为思路来解决这个问题的，arr 每个位置存的不再是元素，而是 TreeMap
TreeMap 的 key 是快照 id, value 是该快照下位置上的值， 如果在两次快照间插入的值没有发生改变，就取前一次的就好了。

比如
```text
snap = 0  arr=[]

1. set: (0, 5) arr = [{snap=0:5}]
2. snap()  snap=1
3. set: (0, 6) arr = [{snap=1:6, 0:5}]
4. get(0, 0)   return arr[0][0]=5
5. get(0, 1)   return arr[0][1]=6  留意实际取得时候用的是 floor， 比如在 snap 为 1 时没有插入就要取到 0 次快照元素了
```
question:
1. 对应位置上没有元素时呢？
2. 重复元素时是不是允许我更新呢？
3. snap 会有多少次呢， 可能会 overflow 吗？
4. 这个数据结构需要线程安全吗？

```kotlin
class SnapshotArray(length: Int) {

    val arr = MutableList<TreeMap<Int, Int>>(length) { TreeMap() }

    var snap = 0
    
    fun set(index: Int, `val`: Int) {
        if (index !in arr.indices) error("Illegal index access: $index")
        arr[index][snap] = `val`
    }

    fun snap(): Int {
        return snap++
    }

    fun get(index: Int, snap_id: Int): Int {
        if (index !in arr.indices) error("Illegal index access: $index")
        val key = arr[index].floorKey(snap_id)
        return if (key == null)  0 else arr[index].getValue(key)
    }

}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/snapshot-array/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
