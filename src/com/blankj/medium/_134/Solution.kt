package com.blankj.medium._134

import com.blankj.ext.print


class Solution {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var minSpare = Int.MAX_VALUE
        var minIndex = 0
        var spare = 0
        for (i in gas.indices) {
            spare += gas[i] - cost[i]
            if (spare < minSpare) {
                minIndex = i
                minSpare = spare
            }
        }
        return if (spare < 0) -1 else (minIndex + 1) % gas.size
    }
}

fun main() {
    Solution().canCompleteCircuit(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(3, 4, 5, 1, 2)
    ).print()
    Solution().canCompleteCircuit(
            intArrayOf(3, 1, 1),
            intArrayOf(1, 2, 2)
    ).print()
}