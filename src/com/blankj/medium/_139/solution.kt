package com.blankj.medium._139


class Solution {

    // Time-Complexity: O(n^2)
    // Space-Complexity: O(n)
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dp = Array(s.length + 1) { false }
        dp[0] = true
        for (i in 1..s.length)
            for (j in 0 until i)
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true
                    break
                }
        return dp[s.length]
    }
}