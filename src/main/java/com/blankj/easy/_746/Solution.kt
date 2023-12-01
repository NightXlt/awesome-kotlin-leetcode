package com.blankj.easy._746

import com.blankj.ext.print
import kotlin.math.min

class Solution {
    fun minCostClimbingStairs(cost: IntArray): Int {
        if (cost.isEmpty()) error("Empty array!")
        val dp = IntArray(cost.size + 1)
        dp[0] = 0
        dp[1] = 0
        for (i in 2..cost.size) {
            dp[i] = min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1])
        }
        return dp.last()
    }

    fun minCostClimbingStairsOptSpace(cost: IntArray): Int {
        if (cost.isEmpty()) error("Empty array!")
        var prev = 0
        var cur = 0
        for (i in 2..cost.size) {
            val next = min(prev + cost[i - 2], cur + cost[i - 1])
            prev = cur
            cur = next
        }
        return cur
    }
}

fun main() {
    Solution().minCostClimbingStairsOptSpace(intArrayOf(10, 15, 20)).print()
    Solution().minCostClimbingStairsOptSpace(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1)).print()
}