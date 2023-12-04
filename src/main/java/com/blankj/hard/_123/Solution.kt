package com.blankj.hard._123

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0
        var buy1 = prices[0]
        var buy2 = prices[0]
        var sell1 = 0
        var sell2 = 0
        for (i in 1..<prices.size) {
            buy1 = min(buy1, prices[i])
            sell1 = max(sell1, prices[i] - buy1)
            buy2 = min(buy2, prices[i] - sell1)
            sell2 = max(sell2, prices[i] - buy2)
        }
        return sell2
    }
}

fun main() {
    Solution().maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4)).print()
    Solution().maxProfit(intArrayOf(1,2,3,4,5)).print()
}