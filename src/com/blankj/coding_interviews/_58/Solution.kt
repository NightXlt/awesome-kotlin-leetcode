package com.blankj.coding_interviews._58

import com.blankj.coding_interviews._004.print

class Solution {
    fun reverseWords(s: String?): String {
        if (s.isNullOrEmpty()) return ""
        val strings: List<String> = s.split(" ")//匹配
        val sb = StringBuilder()
        var t: String
        for (i in strings.indices.reversed()) {
            t = strings[i].trim()
            if (t.isNotEmpty() && i != strings.size - 1) sb.append(' ')
            sb.append(t)
        }
        return sb.toString().trim()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Solution().reverseWords("the sky is blue").print()
            Solution().reverseWords("  hello world!  ").print()
        }
    }
}