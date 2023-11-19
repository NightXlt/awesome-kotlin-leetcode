package com.blankj.easy._935

import com.blankj.ext.print

class Solution {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val orderArray = IntArray(26)
        for ((i, c) in order.withIndex()) {
            orderArray[c - 'a'] = i
        }
        for (i in 0..<words.size - 1) {
            if (!isSorted(words[i], words[i + 1], orderArray)) {
                return false
            }
        }
        return true
    }

    private fun isSorted(word1: String, word2: String, orderArray: IntArray): Boolean {
        var i = 0
        while (i < word1.length && i < word2.length) {
            val c1 = word1[i]
            val c2 = word2[i]
            if (orderArray[c1 - 'a'] < orderArray[c2 - 'a']) {
                return true
            }
            if (orderArray[c1 - 'a'] > orderArray[c2 - 'a']) {
                return false
            }
            i++
        }
        return i == word1.length
    }
}

fun main() {
    Solution().isAlienSorted(
        arrayOf("hello", "leetcode"),
        "hlabcdefgijkmnopqrstuvwxyz"
    ).print()
    Solution().isAlienSorted(
        arrayOf("word", "world", "row"),
        "worldabcefghijkmnpqstuvxyz"
    ).print()
}