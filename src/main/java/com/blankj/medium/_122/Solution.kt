package com.blankj.medium._122

import com.blankj.ext.print

class Solution {
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0
        var min = prices[0]
        var maxProfit = 0
        for (i in 1..<prices.size) {
            if (prices[i] - min > 0) {
                maxProfit += (prices[i] - min)
            }
            min = prices[i]
        }
        return maxProfit
    }
}

fun main() {
    Solution().maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)).print()
}