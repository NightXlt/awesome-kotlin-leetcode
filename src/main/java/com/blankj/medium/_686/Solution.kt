package com.blankj.medium._686


class Solution {
    fun repeatedStringMatch(a: String, b: String): Int {
        val counts = IntArray(26)
        a.forEach { counts[it - 'a']++ }
        if (b.any { counts[it - 'a'] == 0 }) return -1
        val res = b.length / a.length
        val builder = StringBuilder(a.repeat(res))
        for (i in 0..2) {
            if (builder.indexOf(b) >= 0) {
                return res + i
            }
            builder.append(a)
        }
        return -1
    }
}