package com.blankj.medium._56

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun canJump(nums: IntArray): Boolean {
        if (nums.size <= 1) return true
        var rightMost = 0
        for (i in nums.indices) {
            if (i <= rightMost) {
                rightMost = max(rightMost, i + nums[i])
            }
            if (rightMost >= nums.lastIndex) {
                return true
            }
        }
        return false
    }
}

fun main() {
    Solution().canJump(intArrayOf(2, 3, 1, 1, 4)).print()
    Solution().canJump(intArrayOf(3,2,1,0,4)).print()
}