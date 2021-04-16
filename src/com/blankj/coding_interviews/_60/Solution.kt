package com.blankj.coding_interviews._60

import com.blankj.coding_interviews._004.print

class Solution {
    fun dicesProbability(n: Int): DoubleArray {
        if (n <= 0) return doubleArrayOf()
        var dp = DoubleArray(6) { 1.0 / 6 }
        for (i in 2..n) {
            val temp = DoubleArray(5 * i + 1)
            for (j in dp.indices)
                for (k in 0..5)
                    temp[j + k] += dp[j] / 6.0
            dp = temp
        }
        return dp
    }
}

fun main() {
    Solution().dicesProbability(1).print()
    Solution().dicesProbability(2).print()
}