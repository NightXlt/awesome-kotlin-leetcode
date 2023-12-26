# [Minimum Area Rectangle][title]

## Solution
通过 treeMap 维护 x和多个 y 的映射关系， 以 x 进行递增

此外额外维护一个访问过的 x,dy(y2 - y1) 的映射，如果发现当前计算的 x', dy 已经出现过了，说明可以构成矩形。

因为 treeMap 是根据 x 进行排序的，总是比较的是邻近的 x, 故可以求出最小值来

```kotlin
import java.util.*

class Solution {
    fun minAreaRect(points: Array<IntArray>): Int {
        val map = TreeMap<Int, MutableList<Int>>()
        points.forEach {
            map.getOrPut(it[0]) { mutableListOf() }
                .add(it[1])
        }
        val visited = mutableMapOf<Int, Int>()
        var res = Int.MAX_VALUE
        for ((x, row) in map) {
            row.sort()
            for (i in row.indices) {
                for (j in i + 1..<row.size) {
                    val y1 = row[i]
                    val y2 = row[j]
                    val hash = 1001 * y1 + y2
                    if (hash in visited) {
                        res = res.coerceAtMost((x - visited.getValue(hash)) * (y2 - y1))
                    }
                    visited[hash] = x
                }
            }
        }
        return if (res == Int.MAX_VALUE) 0 else res
    }
}
```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/minimum-area-rectangle/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
