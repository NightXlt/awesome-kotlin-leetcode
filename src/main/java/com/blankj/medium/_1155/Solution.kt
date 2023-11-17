package com.blankj.medium._1155

import com.blankj.ext.print


class Solution {
    fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
        val dp = Array(n + 1) { IntArray(target + 1) }
        dp[0][0] = 1
        for (i in 1..n) {
            for (j in 1..target) {
                for (x in 1..k) {
                    if (j - x >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - x]) % 1000000007
                    }
                }
            }
        }
        return dp[n][target]
    }
}

fun main() {
    Solution().numRollsToTarget(1, 6, 3).print()
    Solution().numRollsToTarget(2, 6, 7).print()
}