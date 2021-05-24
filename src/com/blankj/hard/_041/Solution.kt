package com.blankj.hard._041

import com.blankj.coding_interviews._004.print
import kotlin.math.max

class Solution {
    fun firstMissingPositive(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        if (!nums.contains(1)) return 1
        for (i in nums.indices) {
            while (nums[i] in 1..nums.size && nums[nums[i] - 1] != nums[i]) {
                nums[i] = nums[nums[i] - 1].also { nums[nums[i] - 1] = nums[i] }
            }
        }
        for (i in nums.indices) {
            if (i + 1 != nums[i]) {
                return i + 1
            }
        }
        return nums.size + 1
    }
}

fun main() {
    Solution().firstMissingPositive(intArrayOf(3, 4, -1, 1)).print()
    Solution().firstMissingPositive(intArrayOf(1, 2, 3, 4)).print()
}