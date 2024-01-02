package com.blankj.hard._188

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxProfit(k: Int, prices: IntArray): Int {
        if (prices.isEmpty()) return 0
        val k = min(k, prices.size / 2)
        val buy = Array(prices.size) { IntArray(k + 1) }
        val sell = Array(prices.size) { IntArray(k + 1) }
        buy[0][0] = prices[0]
        sell[0][0] = 0
        for (i in 1..k) {
            buy[0][i] = Int.MAX_VALUE / 2
            sell[0][i] = Int.MIN_VALUE / 2
        }
        for (i in 1..<prices.size) {
            buy[i][0] = min(buy[i - 1][0], prices[i] - sell[i - 1][0])
            for (j in 1..k) {
                buy[i][j] = min(buy[i - 1][j], prices[i] - sell[i - 1][j])
                sell[i][j] = max(sell[i - 1][j], prices[i] - buy[i - 1][j - 1])
            }
        }
        return sell.last().max()
    }
}

fun main() {
    Solution().maxProfit(
        4, intArrayOf(2, 4, 1)
    ).print()
    Solution().maxProfit(
        2, intArrayOf(3,2,6,5,0,3)
    ).print()
}