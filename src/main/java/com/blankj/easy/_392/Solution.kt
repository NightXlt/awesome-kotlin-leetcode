package com.blankj.easy._392

import com.blankj.ext.print

class Solution {
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true
        if (s.length > t.length) return false
        var i = 0
        var j = 0
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++
            }
            j++
        }
        return i == s.length
    }

    fun isSubsequenceFollowUp(s: String, t: String): Boolean {
        val m = t.length
        val dp = Array(m + 1) { IntArray(26) }
        for (i in 0..<26) {
            dp[m][i] = m
        }
        for (i in t.indices.reversed()) {
            for (j in 0..<26) {
                if (t[i] == 'a' + j) {
                    dp[i][j] = i
                } else {
                    dp[i][j] = dp[i + 1][j]
                }
            }
        }
        var indexOfT = 0
        for (i in s.indices) {
            if (dp[indexOfT][s[i] - 'a'] == m) {
                return false
            }
            indexOfT = dp[indexOfT][s[i] - 'a'] + 1
        }
        return true
    }
}

fun main() {
    Solution().isSubsequence("abc", "ahbgdc").print()
    Solution().isSubsequence("axc", "ahbgdc").print()
}