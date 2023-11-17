package com.blankj.medium._438

import com.blankj.ext.print


class Solution {
    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.length < p.length) return emptyList()
        val count1 = IntArray(26)
        val count2 = IntArray(26)
        for (i in p.indices) {
            count1[p[i] - 'a']++
            count2[s[i] - 'a']++
        }
        var res = mutableListOf<Int>()
        if (isEquals(count1, count2)) {
            res.add(0)
        }
        for (i in 0 until s.length - p.length) {
            count2[s[i] - 'a']--
            count2[s[i + p.length] - 'a']++
            if (isEquals(count1, count2)) {
                res.add(i + 1)
            }
        }
        return res
    }

    private fun isEquals(count1: IntArray, count2: IntArray): Boolean {
        return count1.indices.all { count1[it] == count2[it] }
    }
}

fun main() {
    Solution().findAnagrams("cbaebabacd", "abc").print()
    Solution().findAnagrams("abab", "ab").print()
    Solution().findAnagrams("baa", "aa").print()
}