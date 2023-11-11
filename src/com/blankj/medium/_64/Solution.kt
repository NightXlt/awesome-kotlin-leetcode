package com.blankj.medium._64

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import kotlin.math.min

class Solution {
    fun minPathSum(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val dp = IntArray(grid[0].size)
        dp[0] = grid[0][0]
        for (i in 1 until grid[0].size) {
            dp[i] = dp[i - 1] + grid[0][i]
        }
        for (i in 1 until grid.size) {
            dp[0] += grid[i][0]
            for (j in 1 until grid[0].size) {
                dp[j] = min(dp[j - 1], dp[j]) + grid[i][j]
            }
        }
        return dp.last()
    }
}

fun main() {
    Solution().minPathSum(
        MultiDimensionArray.createTestData("[[1,3,1],[1,5,1],[4,2,1]]")
    ).print()
    Solution().minPathSum(
        MultiDimensionArray.createTestData("[[1,2,3],[4,5,6]]")
    ).print()
}