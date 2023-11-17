package com.blankj.hard._115

import com.blankj.ext.print
import kotlin.math.min

class Solution {
    fun numDistinct(s: String, t: String): Int {
        if (s.length < t.length) return 0
        val dp = Array(s.length + 1) { IntArray(t.length + 1) }
        for (i in s.indices) {
            dp[i][0] = 1
        }
        for (i in t.indices) {
            dp[0][i] = 0
        }
        dp[0][0] = 1
        for (i in s.indices) {
            for (j in 0 until min(i + 1, t.length)) {
                if (s[i] == t[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1]
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1]
                }
            }
        }
        return dp[s.length][t.length]
    }

    fun numDistinctWithLessSpace(s: String, t: String): Int {
        if (s.length < t.length) return 0
        val dp = IntArray(t.length + 1)
        if (s.isNotEmpty()) {
            dp[0] = 1
        }
        for (i in s.indices) {
            for (j in min(i, t.length - 1) downTo 0) {
                if (s[i] == t[j]) {
                    dp[j + 1] += dp[j]
                }
            }
        }
        return dp[t.length]
    }

}

fun main() {
    Solution().numDistinct("rabbbit", "rabbit").print()
    Solution().numDistinct("babgbag", "bag").print()
}