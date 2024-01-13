package com.blankj.easy._1768

class Solution {
    fun mergeAlternately(word1: String, word2: String): String {
        val builder = StringBuilder()
        var i = 0
        var j = 0
        var k = 0
        while (i < word1.length || j < word2.length) {
            if (i < word1.length) {
                builder[k++] = word1[i++]
            }
            if (j < word2.length) {
                builder[k++] = word2[j++]
            }
        }
        return builder.toString()
    }
}