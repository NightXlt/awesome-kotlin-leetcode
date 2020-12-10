package com.blankj.coding_interviews._260

import com.blankj.coding_interviews._004.print

class SingleNumberII {
    fun singleNumber(nums: IntArray?): Int {
        if (nums == null || nums.isEmpty()) {
            return Int.MIN_VALUE
        }
        var result = 0
        for (i in 0..31) {
            var sum = 0
            for (num in nums) {
                if (num shr i and 1 == 1) {
                    sum++
                }
            }
            if (sum % 3 != 0) {
                result = result or (1 shl i)
            }
        }
        return result
    }
}

fun main() {
    SingleNumberII().singleNumber(intArrayOf(2, 2, 3, 2)).print()
    SingleNumberII().singleNumber(intArrayOf(0, 1, 0, 1, 0, 1, 99)).print()
}