package com.blankj.easy._242

import com.blankj.ext.print

class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val hashTable = IntArray(26)
        for (c in s) {
            if (!c.isLowerCase()) {
                error("Illegal char: $c")
            }
            hashTable[c - 'a']++
        }
        for (c in t) {
            if (!c.isLowerCase()) {
                error("Illegal char: $c")
            }
            if (hashTable[c - 'a'] == 0) {
                return false
            }
            hashTable[c - 'a']--
        }
        return true
    }
}

fun main() {
    Solution().isAnagram(s = "anagram", t = "nagaram").print()
    Solution().isAnagram(s = "rat", t = "car").print()

}