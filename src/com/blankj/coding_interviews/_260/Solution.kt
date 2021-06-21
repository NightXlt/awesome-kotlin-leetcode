package com.blankj.coding_interviews._260

import com.blankj.coding_interviews._004.print

class Solution {
    fun singleNumber(nums: IntArray): IntArray {
        val res = IntArray(2)
        val xorRes = nums.fold(0) { cur, acc ->
            acc xor cur
        }
        val diff = getLowestBits(xorRes)
        res[0] = 0
        nums.forEach {
            if (it and diff != 0) {
                res[0] = res[0] xor it
            }
        }
        res[1] = xorRes xor res[0]
        return res
    }

    // 获取数字只保留最低位二进制 1。参考 Integer.lowestOneBit; 学习highestOneBit。 比如哈 6 的二进制是 110， -6 的二进制是 010. 计算得到010. 只保留数字最低位的 1
    private fun getLowestBits(xorRes: Int): Int {
        return xorRes and -xorRes
    }
}

fun main() {
    Solution().singleNumber(intArrayOf(1, 2, 1, 3, 2, 5)).print()
}
