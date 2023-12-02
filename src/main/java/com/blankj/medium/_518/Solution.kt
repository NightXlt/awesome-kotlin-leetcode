package com.blankj.medium._518

import com.blankj.ext.print

class Solution {
    fun change(amount: Int, coins: IntArray): Int {
        if (coins.isEmpty()) return 0
        val dp = IntArray(amount + 1)
        dp[0] = 1
        for (coin in coins) {
            for (i in coin..amount) {
                dp[i] += dp[i - coin]
            }
        }
        return dp[amount]
    }
}

fun main() {
    Solution().change(5, intArrayOf(1, 2, 5)).print()
}