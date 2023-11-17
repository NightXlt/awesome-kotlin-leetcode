package com.blankj.coding_interviews._58_2

import com.blankj.ext.print

class Solution {

    fun reverseLeftWords(s: String?, n: Int): String {
        if (n < 0) throw IllegalArgumentException("n must be greater than or equal to 0")
        if (s.isNullOrEmpty()) return ""
        val k = n % s.length
        // 右旋转则用下面的枢轴
//        val partition = s.length - k
        val result = StringBuilder(s)
        reverse(result, 0, k - 1)
        reverse(result, k, result.length - 1)
        reverse(result, 0, result.length - 1)
        return result.toString()
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
}

fun main() {
    Solution().reverseLeftWords("the sky is blue", 2).print()
    Solution().reverseLeftWords("  hello world!  ", 3).print()
    Solution().reverseLeftWords("     ", 4).print()
    Solution().reverseLeftWords("", 0).print()
    Solution().reverseLeftWords(null, 2).print()
}