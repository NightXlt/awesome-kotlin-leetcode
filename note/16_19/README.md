# [Pond Sizes LCCI][title]

## Solution
深搜时需要判断 land 的值进行剪枝，不为零直接跳过即可。这道题并不是迷宫题不需要回溯状态。
```kotlin
package com.blankj.medium._16_19

import java.util.*

class Solution {
    fun dfs(land: Array<IntArray>, row: Int, col: Int): Int {
        if (row < 0 || col < 0 || row >= land.size || col >= land[row].size || land[row][col] != 0) return 0
        land[row][col] = -1
        var count = 1
        count += dfs(land, row, col + 1)
        count += dfs(land, row, col - 1)
        count += dfs(land, row - 1, col)
        count += dfs(land, row + 1, col)
        count += dfs(land, row - 1, col + 1)
        count += dfs(land, row - 1, col - 1)
        count += dfs(land, row + 1, col + 1)
        count += dfs(land, row + 1, col - 1)
        return count
    }

    fun pondSizes(land: Array<IntArray>): IntArray {
        if (land.isEmpty()) return intArrayOf()
        var count: Int
        val queue = PriorityQueue<Int>()
        for (row in land.indices) {
            for (col in land[row].indices) {
                if (land[row][col] == 0) {
                    count = dfs(land, row, col)
                    queue.add(count)
                }

            }
        }
        return IntArray(queue.size) { queue.poll() }
    }

}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/pond-sizes-lcci/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
