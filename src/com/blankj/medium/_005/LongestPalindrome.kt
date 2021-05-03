package com.blankj.medium._005

import com.blankj.coding_interviews._004.print

class LongestPalindrome {
    var leftIndex = 0
    var rightIndex = 0
    var maxLength = 0

    fun longestPalindrome(s: String): String {
        if (s.isNullOrEmpty() || s.length <= 1) return s
        for (i in s.indices) {
            traversalDoubleSide(s, i, i)
            traversalDoubleSide(s, i, i + 1)
        }
        return s.substring(leftIndex..rightIndex)
    }

    private fun traversalDoubleSide(s: String, low: Int, high: Int) {
        var low = low
        var high = high
        while (low >= 0 && high <= s.length - 1) {
            if (s[low] != s[high]) break
            val length = high - low + 1
            if (length > maxLength) {
                maxLength = length
                leftIndex = low
                rightIndex = high
            }
            low--
            high++
        }
    }
}

fun main() {
    LongestPalindrome().longestPalindrome("").print()
    LongestPalindrome().longestPalindrome("a").print()
    LongestPalindrome().longestPalindrome("babad").print()
    LongestPalindrome().longestPalindrome("cbbd").print()
}