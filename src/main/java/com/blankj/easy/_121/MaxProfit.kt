package com.blankj.easy._121

class MaxProfit {
    fun maxProfit(prices: IntArray): Int {
        if (prices.size <= 1) return 0
        var maxProfit = 0
        var min = prices[0]
        for (i in 1..<prices.size) {
            min = min.coerceAtMost(prices[i])
            maxProfit = maxProfit.coerceAtLeast(prices[i] - min)
        }
        return maxProfit
    }
}