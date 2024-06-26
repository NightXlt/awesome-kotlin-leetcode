package com.blankj.medium._139


class Solution {

    // Time-Complexity: O(n^2)
    // Space-Complexity: O(n)
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dp = BooleanArray(s.length + 1)
        dp[0] = true

        // Use set to make sure get element is O(1)
        val set = wordDict.toSet()
        for (i in 1..s.length) {
            for (j in 0..<i) {
                if (dp[j] && s.substring(j, i) in set) {
                    dp[i] = true
                    break
                }
            }
        }
        return dp.last()
    }
}