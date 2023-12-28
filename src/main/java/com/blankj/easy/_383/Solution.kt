package com.blankj.easy._383

import com.blankj.ext.print

class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val countRansom = IntArray(26)
        val countMagazine = IntArray(26)
        ransomNote.forEach { countRansom[it - 'a']++ }
        magazine.forEach { countMagazine[it - 'a']++ }
        for ((i, count) in countRansom.withIndex()) {
            if (count > countMagazine[i]) {
                return false
            }
        }
        return true
    }
}

fun main() {
    Solution().canConstruct("a", "b").print()
    Solution().canConstruct("aa", "aab").print()
}