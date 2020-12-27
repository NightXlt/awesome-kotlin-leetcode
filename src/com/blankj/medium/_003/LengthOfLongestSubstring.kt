package com.blankj.medium._003

import com.blankj.coding_interviews._004.print

class LengthOfLongestSubstring {

    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        val hashMap = IntArray(128)
        var startIndex = 0
        var max = 0
        for ((index, c) in s.withIndex()) {
            val cInt = c.toInt()
            val nearestPreCIndex = hashMap[cInt]
            if (nearestPreCIndex >= startIndex) { // 遇到重复字符时更新起始节点
                startIndex = nearestPreCIndex
            }
            val l = index - startIndex + 1
            hashMap[cInt] = index + 1
            if (l > max) max = l
        }
        return max
    }
}

fun main() {
    LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb").print()
    LengthOfLongestSubstring().lengthOfLongestSubstring("bbbbb").print()
    LengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew").print()
    LengthOfLongestSubstring().lengthOfLongestSubstring("Abcabcbb").print()
}