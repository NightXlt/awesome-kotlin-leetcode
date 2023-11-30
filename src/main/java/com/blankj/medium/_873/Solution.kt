package com.blankj.medium._873

import com.blankj.ext.print
import kotlin.math.max


class Solution {
    fun lenLongestFibSubseq(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        val dp = Array(arr.size) { IntArray(arr.size) }
        var res = 2
        val map = arr.mapIndexed { index, i -> i to index }
            .associate { it }
        for (i in 1 until arr.size) {
            for (j in 0 until i) {
                val k = map.getOrDefault(arr[i] - arr[j], -1)
                dp[i][j] = if (k in 0 until j) dp[j][k] + 1 else 2
                res = max(res, dp[i][j])
            }
        }
        return if (res > 2) res else 0
    }
}

fun main() {
    Solution().lenLongestFibSubseq(intArrayOf(1,2,3,4,5,6,7,8)).print()
    Solution().lenLongestFibSubseq(intArrayOf(1,3,7,11,12,14,18)).print()
}