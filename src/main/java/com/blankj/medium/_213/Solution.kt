package com.blankj.medium._213

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) {
            return nums[0];
        } else if (nums.size == 2) {
            return max(nums[0], nums[1]);
        }
        return max(
            helper(nums, 0, nums.size - 2),
            helper(nums, 1, nums.size - 1)
        )
    }

    fun helper(nums: IntArray, start: Int, end: Int): Int {
        var first = nums[start]
        var second = max(nums[start], nums[end])
        for (i in start + 2..end) {
            val temp = second
            second = max(second, first + nums[i])
            first = temp
        }
        return second
    }
}

fun main() {
    Solution().rob(intArrayOf(1, 2, 3, 1)).print()
    Solution().rob(intArrayOf(2, 7, 9, 3, 1)).print()
}