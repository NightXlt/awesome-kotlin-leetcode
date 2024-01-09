package com.blankj.easy._66

import com.blankj.ext.print

class Solution {
    fun plusOne(digits: IntArray): IntArray {
        var canary = 0
        for (i in digits.indices.reversed()) {
            canary = (digits[i] + 1) / 10
            digits[i] = (digits[i] + 1) % 10
            if (canary == 0) {
                return digits
            }
        }
        val res = IntArray(digits.size + 1)
        digits.copyInto(res, startIndex = 1)
        res[0] = 1
        return res
    }
}

fun main() {
    Solution().plusOne(intArrayOf(1, 2, 3)).print()
    Solution().plusOne(intArrayOf(9)).print()
}