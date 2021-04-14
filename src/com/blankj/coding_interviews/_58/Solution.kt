package com.blankj.coding_interviews._58

import com.blankj.coding_interviews._004.print

class Solution {
    fun reverseWords(s: String): String {
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

    fun reverseWordsNative(s: String?): String {
        if (s.isNullOrEmpty()) return ""
        val result = trimSpace(s)
        reverse(result, 0, result.length - 1)
        reverseEachWords(result)
        return result.toString()
    }

    private fun trimSpace(s: String): StringBuilder {
        val stringBuilder = StringBuilder()
        var left = 0
        var right = s.length - 1
        while (left <= right && s[left] == ' ') {
            left++
        }
        while (left <= right && s[right] == ' ') {
            right--
        }
        while (left <= right) {
            val c = s[left]
            if (c != ' ') {
                stringBuilder.append(c)
            } else if (stringBuilder.last() != ' ') {
                stringBuilder.append(' ')
            }
            left++
        }
        return stringBuilder
    }

    private fun reverse(stringBuilder: StringBuilder, left: Int, right: Int) {
        var left = left
        var right = right
        while (left < right) {
            val leftChar = stringBuilder[left]
            stringBuilder[left++] = stringBuilder[right]
            stringBuilder[right--] = leftChar
        }
    }

    private fun reverseEachWords(stringBuilder: StringBuilder) {
        val l = stringBuilder.length
        var wordStart = 0
        var wordEnd = 0
        while (wordStart < l) {
            while (wordEnd < l && stringBuilder[wordEnd] != ' ') wordEnd++
            reverse(stringBuilder, wordStart, wordEnd - 1)
            wordStart = ++wordEnd
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Solution().reverseWordsNative("the sky is blue").print()
            Solution().reverseWordsNative("  hello world!  ").print()
            Solution().reverseWordsNative("     ").print()
            Solution().reverseWordsNative("").print()
            Solution().reverseWordsNative(null).print()
        }
    }
}