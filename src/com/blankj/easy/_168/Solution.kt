package com.blankj.easy._168

import com.blankj.coding_interviews._004.print

class Solution {

    fun convertToTitle(columnNumber: Int): String {
        if (columnNumber <= 0) return ""
        val res = StringBuilder()
        var num = columnNumber
        while (num != 0) {
            num--
            val curChar = 'A' + (num % 26)
            res.insert(0, curChar)
            num /= 26
        }
        return res.toString()
    }
}

fun main() {
    Solution().convertToTitle(0).print()
    Solution().convertToTitle(1).print()
    Solution().convertToTitle(28).print()
    Solution().convertToTitle(701).print()
}