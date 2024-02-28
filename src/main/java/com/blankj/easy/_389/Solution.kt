package com.blankj.easy._389

import com.blankj.ext.print
import kotlin.math.min

class Solution {

    fun diffBetweenTwoStrings(source: String, target: String): Array<String> {
        val dp = minDistance(source, target)
        val res = mutableListOf<String>()
        var i = 0
        var j = 0
        while (i < source.length && j < target.length) {
            if (source[i] == target[j]) {
                res.add(source[i].toString())
                i++
                j++
                continue
            }
            if (dp[i + 1][j] <= dp[i][j + 1]) {
                res.add("-${source[i]}")
                i++
            } else {
                res.add("+${target[j]}")
                j++
            }
        }
        while (j < target.length) {
            res.add("+${target[j]}")
            j++
        }
        return res.toTypedArray()
    }

    private fun minDistance(word1: String, word2: String): Array<IntArray> {
        val length1 = word1.length + 1
        val length2 = word2.length + 1
        val dp = Array(length1) {
            IntArray(length2)
        }
//    # dp(i, j) is the minimum edits to transform
//    # string source[i:] into string target[j:].
        repeat(length1) { i -> dp[i][word2.length] = word1.length - i }
        repeat(length2) { j -> dp[word1.length][j] = word2.length - j }
        for (i in word1.indices.reversed()) {
            for (j in word2.indices.reversed()) {
                if (word1[i] == word2[j]) {
                    dp[i][j] = dp[i + 1][j + 1]
                } else {
                    dp[i][j] = 1 + min(dp[i + 1][j], dp[i][j + 1])
                }
            }
        }
        return dp
    }

}

fun main() {
    Solution().diffBetweenTwoStrings("CABAAABBC", "CBBC").print()
    Solution().diffBetweenTwoStrings("ABCDEFG", "ABDFFGH").print()
}