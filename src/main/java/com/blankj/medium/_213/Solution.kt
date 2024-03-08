package com.blankj.medium._213

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size <= 2) return nums.max()
        return max(
            helper(nums, 0, nums.size - 2),
            helper(nums, 1, nums.size - 1)
        )
    }
    private fun helper(nums: IntArray, start: Int, end: Int): Int {
        var first = nums[start]
        var second = max(nums[start], nums[start + 1])
        for (i in start+2..end) {
            val temp = second
            second = max(first + nums[i], second)
            first = temp
        }
        return second
    }
}

fun main() {
    Solution().rob(intArrayOf(1, 2, 3, 1)).print()
    Solution().rob(intArrayOf(2, 7, 9, 3, 1)).print()
}