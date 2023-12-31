package com.blankj.easy._290

import com.blankj.ext.print

class Solution {
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")
        if (pattern.length != words.size) return false
        val mapping = mutableMapOf<Char, String>()
        val reversedMapping = mutableMapOf<String, Char>()
        for (i in pattern.indices) {
            val c = pattern[i]
            val word = words[i]
            if (c in mapping && mapping.getValue(c) != word || word in reversedMapping && reversedMapping.getValue(word) != c) {
                return false
            }
            mapping[c] = word
            reversedMapping[word] = c
        }
        return true
    }
}

fun main() {
    Solution().wordPattern("abba", "dog cat cat dog").print()
    Solution().wordPattern("abba", "dog cat cat fish").print()
}