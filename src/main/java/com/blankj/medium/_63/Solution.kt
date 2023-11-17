package com.blankj.medium._63

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {

    fun uniquePathsWithObstacles(grid: Array<IntArray>): Int {
        if (grid.isEmpty() || grid[0][0] == 1) return 0
        val rows = grid.size
        val cols = grid[0].size
        val dp = IntArray(cols)
        dp[0] = 1
        for (i in 1 until cols) dp[i] = if (grid[0][i] == 0 && dp[i - 1] == 1) 1 else 0
        for (i in 1 until rows) {
            dp[0] = if (grid[i][0] == 0 && dp[0] == 1) 1 else 0
            for (j in 1 until cols)
                dp[j] = if (grid[i][j] == 0) dp[j] + dp[j - 1] else 0
        }
        return dp[cols - 1]
    }

}

fun main() {
    Solution().uniquePathsWithObstacles(
        MultiDimensionArray.createTestData("[[0,0,0],[0,1,0],[0,0,0]]")
    ).print()
    Solution().uniquePathsWithObstacles(
        MultiDimensionArray.createTestData("[[0,1],[0,0]]")
    ).print()
    Solution().uniquePathsWithObstacles(
        MultiDimensionArray.createTestData("[[0],[1]]")
    ).print()
    Solution().uniquePathsWithObstacles(
        MultiDimensionArray.createTestData("[[0,0],[1,0]]")
    ).print()
    Solution().uniquePathsWithObstacles(
        MultiDimensionArray.createTestData("[[0,0],[1,1],[0,0]]")
    ).print()
    Solution().uniquePathsWithObstacles(
        MultiDimensionArray.createTestData("[[1]]")
    ).print()
}