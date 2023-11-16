package com.blankj.easy._125

import com.blankj.ext.print

class Solution {
    fun isPalindrome(s: String): Boolean {
        var i = 0
        var j = s.length - 1
        while (i < j) {
            when {
                !Character.isLetterOrDigit(s[i]) -> i++
                !Character.isLetterOrDigit(s[j]) -> j--
                else -> {
                    val leftChar = s[i].toLowerCase()
                    val rightChar = s[j].toLowerCase()
                    if (leftChar != rightChar) return false
                    i++
                    j--
                }
            }
        }
        return true
    }
}

fun main() {
    Solution().isPalindrome(" ").print()
    Solution().isPalindrome("A man, a plan, a canal: Panama").print()
    Solution().isPalindrome("race a car").print()
}