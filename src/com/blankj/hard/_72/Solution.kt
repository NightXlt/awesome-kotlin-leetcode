package com.blankj.hard._72

import com.blankj.coding_interviews._004.print
import kotlin.math.min

class Solution {
    fun minDistance(word1: String, word2: String): Int {
        val length1 = word1.length + 1
        val length2 = word2.length + 1
        val dp = Array(length1) {
            IntArray(length2)
        }
        repeat(length1) { i -> dp[i][0] = i }
        repeat(length2) { j -> dp[0][j] = j }
        for (i in 1 until length1) {
            for (j in 1 until length2) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                }
            }
        }
        return dp[word1.length][word2.length]
    }

    private fun min(a: Int, b: Int, c: Int): Int = min(min(a, b), c)
}

fun main() {
    Solution().minDistance("horse", "ros").print()
    Solution().minDistance("intention", "execution").print()
}