package com.blankj.easy._058

class Solution {
    fun lengthOfLastWord(s: String): Int {
        var end = s.indexOfLast { it != ' ' }
        if (end < 0) return 0
        var start = end
        while (start >= 0 && s[start] != ' ') {
            start--
        }
        return end - start
    }
}