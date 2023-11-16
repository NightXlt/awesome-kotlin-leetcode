package com.blankj.easy._680

import com.blankj.ext.print

class Solution {
    fun validPalindrome(s: String): Boolean {
        var start = 0
        var end = s.lastIndex
        while (start < end) {
            if (s[start] != s[end]) {
                break
            }
            start++
            end--
        }
        return start == end ||
                isPalindrome(s, start, end - 1) ||
                isPalindrome(s, start + 1, end)
    }

    private fun isPalindrome(s: String, first: Int, last: Int): Boolean {
        var start = first
        var end = last
        while (start < end) {
            val leftChar = s[start].lowercase()
            val rightChar = s[end].lowercase()
            if (leftChar != rightChar) return false
            start++
            end--
        }
        return true
    }

}

fun main() {
    Solution().validPalindrome("aba").print()
    Solution().validPalindrome("abca").print()
    Solution().validPalindrome("abc").print()
}