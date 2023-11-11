package com.blankj.medium._62

import com.blankj.ext.print

class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) }
        return helper(m - 1, n - 1, dp)
    }

    private fun helper(row: Int, col: Int, dp: Array<IntArray>) : Int{
        if (dp[row][col] == 0) {
            if (row == 0 || col == 0) {
                dp[row][col] = 1
            } else {
                dp[row][col] = helper(row - 1, col, dp) + helper(row, col - 1, dp)
            }
        }
        return dp[row][col]
    }


    fun uniquePathsWithLessSpace(m: Int, n: Int): Int {
        val dp = IntArray(n) { 1 }
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[j] += dp[j - 1]
            }
        }
        return dp[n - 1]
    }


}

fun main() {
    Solution().uniquePaths(3, 7).print()
    Solution().uniquePaths(3, 2).print()
}