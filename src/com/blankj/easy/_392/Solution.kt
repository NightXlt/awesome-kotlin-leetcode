package com.blankj.easy._392

import com.blankj.ext.print

class Solution {
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true
        if (s.length > t.length) return false
        var i = 0
        var j = 0
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++
            }
            j++
        }
        return i == s.length
    }
}

fun main() {
    Solution().isSubsequence("abc", "ahbgdc").print()
    Solution().isSubsequence("axc", "ahbgdc").print()
}