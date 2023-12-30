# [Minimum Number of Arrows to Burst Balloons][title]

## Solution
用区间的 end 进行排序，
初始化 end 为首元素的 end, res = 1 表示射中第一个区间的箭
遍历的过程中， 发现后面区间的 start 比当前的 end 大时， 说明不存在重叠区间了
这时 res + 1， 同时更新当前的 end。遍历结束 res 即为所求。

```kotlin
class Solution {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        if (points.isEmpty()) return 0
        points.sortBy { it[1] }
        var res = 1
        var pos = points[0][1]
        points.forEach {
            if (it[0] > pos) {
                pos = it[1]
                res++
            }
        }
        return res
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
