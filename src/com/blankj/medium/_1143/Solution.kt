package com.blankj.medium._1143

import com.blankj.ext.print
import kotlin.math.max


class Solution {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val len1 = text1.length
        val len2 = text2.length
        if (len1 < len2) {
            return longestCommonSubsequence(text2, text1)
        }
        val dp = Array<IntArray>(2) { IntArray(len2 + 1) }
        for (i in text1.indices) {
            for (j in text2.indices) {
                if (text1[i] == text2[j]) {
                    dp[(i + 1) % 2][j + 1] = dp[i % 2][j] + 1
                } else {
                    dp[(i + 1) % 2][j + 1] = max(dp[i % 2][j + 1], dp[(i + 1) % 2][j])
                }
            }
        }
        return dp[len1 % 2][len2]
    }
}

fun main() {
    Solution().longestCommonSubsequence("abcde", "ace").print()
    Solution().longestCommonSubsequence("abc", "abc").print()
    Solution().longestCommonSubsequence("abc", "def").print()
}