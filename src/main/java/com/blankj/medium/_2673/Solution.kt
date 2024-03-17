package com.blankj.medium._2673

import com.blankj.ext.print
import kotlin.math.abs
import kotlin.math.max

class Solution {
    fun minIncrements(n: Int, cost: IntArray): Int {
        var res = 0
        for (i in n - 2 downTo 1 step 2) {
            res += abs(cost[i] - cost[i + 1])
            cost[i / 2] += max(cost[i], cost[i + 1])
        }
        return res
    }
}

fun main() {
    Solution().minIncrements(7, intArrayOf(1, 5, 2, 2, 3, 3, 1)).print()
}