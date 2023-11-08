package com.blankj.medium._256

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import kotlin.math.min

class Solution {
    fun minCost(costs: Array<IntArray>): Int {
        if (costs.isEmpty()) return 0
        val dp = Array<IntArray>(3) { IntArray(2) }
        for (i in 0..2) {
            dp[i][0] = costs[0][i]
        }
        for (i in 1 until costs.size) {
            for (j in 0..2) {
                val prev1 = dp[(j + 2) % 3][(i - 1) % 2]
                val prev2 = dp[(j + 1) % 3][(i - 1) % 2]
                dp[j][i % 2] = min(prev1, prev2) + costs[i][j]
            }
        }
        val lastIndex = (costs.lastIndex) % 2
        return min(dp[0][lastIndex], min(dp[1][lastIndex], dp[2][lastIndex]))
    }
}

fun main() {
    Solution().minCost(
        MultiDimensionArray.createTestData("[[17,2,17],[16,16,5],[14,3,19]]")
    ).print()
    Solution().minCost(
        MultiDimensionArray.createTestData("[[7,6,2]]")
    ).print()
}