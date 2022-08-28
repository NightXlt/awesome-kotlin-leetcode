package com.blankj.coding_interviews._43

import com.blankj.ext.print


class Solution {

    fun countDigitOne(n: Int): Int {
        if (n <= 0) throw IllegalArgumentException("n is less than or equal  0. plz check it")
        var digit = 1
        var high = n / 10
        var cur = n % 10
        var low = 0
        var res = 0
        while (high != 0 || cur != 0) {
            res += when (cur) {
                0 -> {
                    high * digit
                }
                1 -> {
                    high * digit + low + 1 // 1 for 11
                }
                else -> {
                    (high + 1) * digit
                }
            }
            low += cur * digit
            cur = high % 10
            high /= 10
            digit *= 10
        }
        return res
    }
}

fun main() {
//    Solution().countDigitOne(0).print()
    Solution().countDigitOne(204).print()
    Solution().countDigitOne(12).print()
}
