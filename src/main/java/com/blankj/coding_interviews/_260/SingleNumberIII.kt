package com.blankj.coding_interviews._260

import com.blankj.ext.print

class SingleNumberIII {
    fun singleNumbers(nums: IntArray): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)
        val xorRes = nums.fold(0) { cur, acc ->
            acc xor cur
        }
        val diff = xorRes and -xorRes
        var res = 0
        for (num in nums) {
            if (num and diff != 0) {
                res = res xor num
            }
        }
        return intArrayOf(res, xorRes xor res)
    }
}

fun main() {
    SingleNumberIII().singleNumbers(intArrayOf(4, 1, 4, 6)).print()
}

