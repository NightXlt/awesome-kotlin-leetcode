package com.blankj.medium._5417

import kotlin.math.max

class Solution {
    fun maxVowels(s: String, k: Int): Int {
        val vowels = "aeiou"
        var i = 0
        var count = 0
        var maxLen = 0
        for (j in s.indices) {
            if (s[j] in vowels) count++
            if (j > k - 1) {
                if (s[i] in vowels) count--
                i++
            }
            maxLen = max(maxLen, count)
        }
        return maxLen
    }
}