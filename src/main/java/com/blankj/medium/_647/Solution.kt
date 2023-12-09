package com.blankj.medium._647

import com.blankj.ext.print

class Solution {
    // similar with leetcode-5 (longestPalindrome)
    fun countSubstrings(s: String): Int {
        if (s.isEmpty()) return 0
        var count = 0
        for (i in s.indices) {
            count += countPalindrome(s, i, i)
            count += countPalindrome(s, i, i + 1)
        }
        return count
    }

    private fun countPalindrome(s: String, low: Int, high: Int): Int {
        var start = low
        var end = high
        var count = 0
        while (start >= 0 && end < s.length && s[start] == s[end]) {
            count++
            start--
            end++
        }
        return count
    }
}

fun main() {
    Solution().countSubstrings("").print()
    Solution().countSubstrings("abc").print()
    Solution().countSubstrings("aaa").print()
}