package com.blankj.medium._167

import com.blankj.ext.print

class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var low = 0
        var high = numbers.lastIndex
        while (low < high) {
            val sum = numbers[low] + numbers[high]
            when {
                sum > target -> high--
                sum < target -> low++
                else -> return intArrayOf(low + 1, high + 1)
            }
        }
        return intArrayOf(-1, -1)
    }
}

fun main() {
    Solution().twoSum(intArrayOf(2, 7, 11, 15), 9).print()
    Solution().twoSum(intArrayOf(2, 3, 4), 6).print()
}