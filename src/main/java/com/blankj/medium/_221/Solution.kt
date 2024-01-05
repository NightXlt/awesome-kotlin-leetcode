package com.blankj.medium._221

class Solution {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0
        val dp = Array(matrix.size) { IntArray(matrix.first().size) }
        var max = Int.MIN_VALUE
        for (i in matrix.indices) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1
                max = 1
            }
        }

        for (j in matrix.first().indices) {
            if (matrix[0][j] == '1') {
                dp[0][j] = 1
                max = 1
            }
        }
        for (i in 1..<matrix.size) {
            for (j in 1..<matrix.first().size) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = minOf(dp[i -1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
                    max = max.coerceAtLeast(dp[i][j])
                }
            }
        }
        return max * max
    }
}