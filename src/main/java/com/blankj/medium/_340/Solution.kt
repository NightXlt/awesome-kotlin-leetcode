package com.blankj.medium._340

import com.blankj.ext.print

class Solution {
    fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
        if (s.length * k == 0) return 0
        val map = mutableMapOf<Char, Int>()
        var maxLength = 1
        var left = 0
        var right = 0
        while (right < s.length) {
            map[s[right]] = right
            if (map.size == k + 1) {
                val deleteId = map.values.min()
                map.remove(s[deleteId])
                left = deleteId + 1
            }
            maxLength = maxLength.coerceAtLeast(right - left + 1)
            right++
        }
        return maxLength
    }
}

fun main() {
    Solution().lengthOfLongestSubstringKDistinct("eceba", 2).print()
}