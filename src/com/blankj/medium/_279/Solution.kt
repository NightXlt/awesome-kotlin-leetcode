package com.blankj.medium._279

import com.blankj.ext.print
import kotlin.math.min
import kotlin.math.sqrt


class Solution {
    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 1..n) {
            for (j in 1..sqrt(i.toFloat()).toInt()) {
                dp[i] = min(dp[i], dp[i - j * j] + 1)
            }
        }
        return dp[n]
    }
}

fun main() {
    Solution().numSquares(12).print()
    Solution().numSquares(13).print()
}