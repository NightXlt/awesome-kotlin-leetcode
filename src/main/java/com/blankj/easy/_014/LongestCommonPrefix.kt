package com.blankj.easy._014

class LongestCommonPrefix {

    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        val length = strs.first().length
        for (i in 0 until length) {
            val c = strs.first()[i]
            for (j in 1 until strs.size) {
                if (strs[j].getOrNull(i) != c) return strs.first().substring(0, i)
            }
        }
        return strs.first()
    }
}

fun main() {
    println(LongestCommonPrefix().longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    println(LongestCommonPrefix().longestCommonPrefix(arrayOf("dog","racecar","car")))
}