package com.blankj.medium._567

import com.blankj.ext.print


class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        if (s2.length < s1.length) return false
        val count1 = IntArray(26)
        val count2 = IntArray(26)
        for (i in s1.indices) {
            count1[s1[i] - 'a']++
            count2[s2[i] - 'a']++
        }
        if (isEquals(count1, count2)) {
            return true
        }
        for (i in 0 until s2.length - s1.length) {
            count2[s2[i] - 'a']--
            count2[s2[i + s1.length] - 'a']++
            if (isEquals(count1, count2)) {
                return true
            }
        }
        return false
    }

    private fun isEquals(count1: IntArray, count2: IntArray): Boolean {
        return count1.indices.all { count1[it] == count2[it] }
    }
}

fun main() {
    Solution().checkInclusion("ab", "eidbaooo").print()
    Solution().checkInclusion("ab", "eidboaoo").print()
    Solution().checkInclusion("adc", "dcda").print()
}