package com.blankj.medium._322

import com.blankj.coding_interviews._004.print
import kotlin.math.min

class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0
        for (coin in coins) {
            for (i in 0..amount) {
                if (coin <= i) {
                    dp[i] = min(dp[i], dp[i - coin] + 1)
                }
            }
        }
        return if (dp[amount] > amount) -1 else dp[amount]
    }
}

fun main() {
    Solution().coinChange(intArrayOf(186, 419, 83, 408), 6249).print()
    Solution().coinChange(intArrayOf(1, 2, 5), 11).print()
    Solution().coinChange(intArrayOf(2), 1).print()
    Solution().coinChange(intArrayOf(1), 0).print()
    Solution().coinChange(intArrayOf(1), 2).print()
    Solution().coinChange(intArrayOf(), 5).print()
}