package com.blankj.coding_interviews._67

import com.blankj.ext.print

class Solution {

    fun strToInt(str: String?): Int {
        var str = str ?: throw IllegalArgumentException("String is null")
        str = str.trim()
        val len = str.length
        if (str.isEmpty()) {
            throw IllegalArgumentException("String is blank or empty")
        }
        var c = str[0]
        var flag = 1
        var digit = 0L
        var digitLen = 0
        when {
            c == '-' -> {
                flag = -1
            }
            c == '+' -> {
                flag = 1
            }
            Character.isDigit(c) -> {
                digit = (c - '0').toLong()
            }
            // pre char is non-digit char, so return directly
            else -> {
                throw IllegalArgumentException("String contains illegal character")
            }
        }
        for (i in 1 until len) {
            c = str[i]
            if (!Character.isDigit(c)) {
                throw IllegalArgumentException("String contains illegal character")
            } else if (digit == 0L && c == '0') {
                continue
            }
            digit = digit * 10 + c.toLong() - '0'.toLong()
            digitLen++
            // digit is more than Int.MAX_VALUE length
            if (digitLen > 11) {
                break
            }
        }
        digit *= flag.toLong()
        if (digit > Int.MAX_VALUE) {
            return Int.MAX_VALUE
        } else if (digit < Int.MIN_VALUE) {
            return Int.MIN_VALUE
        }
        return digit.toInt()
    }
}

fun main() {
    Solution().strToInt("").print()
    Solution().strToInt(null).print()
    Solution().strToInt("null 42").print()
    Solution().strToInt("42").print()
    Solution().strToInt("   -42").print()
}