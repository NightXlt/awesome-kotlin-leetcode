package com.blankj.google

import com.blankj.ext.print

class CharacterReplacement {
    fun characterReplacement(s: String, k: Int): Int {
        if (s.length < 2) return s.length
        var left = 0
        var right = 0
        var res = 0
        var maxCount = 0
        val freq = IntArray(26)
        while (right < s.length) {
            freq[s[right] - 'A']++
            maxCount = maxCount.coerceAtLeast(freq[s[right] - 'A'])
            if (right - left + 1 > maxCount + k) {
                freq[s[left] - 'A']--
                left++
            }
            res = res.coerceAtLeast(right - left + 1)
            right++
        }
        return res
    }
}

fun main() {
    CharacterReplacement().characterReplacement("ABAB", 2).print()
    CharacterReplacement().characterReplacement("AABABBA", 1).print()
}