package com.blankj.hard._132

import com.blankj.ext.print
import kotlin.math.min

class Solution {
    fun minCut(s: String): Int {
        val isPal = Array(s.length) { BooleanArray(s.length) }
        for (right in s.indices) {
            for (left in 0..right) {
                if (s[right] == s[left] && (right - left <= 2 || isPal[left + 1][right - 1])) {
                    isPal[left][right] = true
                }
            }
        }
        val dp = IntArray(s.length)
        for (i in s.indices) {
            if (isPal[0][i]) {
                dp[i] = 0
            } else {
                dp[i] = i
                for (j in 1..i) {
                    if (isPal[j][i]) {
                        dp[i] = min(dp[i], dp[j - 1] + 1)
                    }
                }
            }
        }
        return dp.last()
    }
}

fun main() {
    Solution().minCut("aab").print()
    Solution().minCut("a").print()
    Solution().minCut("ab").print()
}