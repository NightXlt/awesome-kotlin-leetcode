package com.blankj.easy._205

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val sToT = mutableMapOf<Char, Char>()
        val tToS = mutableMapOf<Char, Char>()
        for (i in s.indices) {
            val c1 = s[i]
            val c2 = t[i]
            if (c1 in sToT && sToT.getValue(c1) != c2 || c2 in tToS && tToS.getValue(c2) != c1) {
                return false
            }
            sToT[c1] = c2
            tToS[c2] = c1
        }
        return true
    }
}

fun main() {
    Solution().isIsomorphic("badc", "baba").print()
}