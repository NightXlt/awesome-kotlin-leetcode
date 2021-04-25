package com.blankj.coding_interviews._63

import com.blankj.coding_interviews._004.print
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxProfit(prices: IntArray): Int {
        if (prices.size <= 1) return 0
        var maxProfit = 0
        // 留意这里的最值不要写反了
        var min = Int.MAX_VALUE
        for (price in prices) {
            min = min(price, min)
            maxProfit = max(maxProfit, price - min)
        }
        return maxProfit
    }
}

fun main() {
    Solution().maxProfit(intArrayOf(7,1,5,3,6,4)).print()
    Solution().maxProfit(intArrayOf(7,6,4,3,1)).print()
    Solution().maxProfit(intArrayOf(10, 17)).print()
    Solution().maxProfit(intArrayOf(10, 10)).print()
    Solution().maxProfit(intArrayOf(0, 0)).print()
    Solution().maxProfit(intArrayOf(4000, 997)).print()
}