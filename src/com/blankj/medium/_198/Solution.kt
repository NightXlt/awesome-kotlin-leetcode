package com.blankj.medium._198

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val dp = IntArray(2)
        dp[0] = nums[0]
        if (nums.size > 1) {
            dp[1] = max(nums[0], nums[1])
        }
        for (i in 2 until nums.size) {
            dp[i % 2] = max(dp[(i - 1) % 2], dp[(i - 2) % 2] + nums[i])
        }
        return dp[nums.lastIndex % 2]
    }
}

fun main() {
    Solution().rob(intArrayOf(1, 2, 3, 1)).print()
    Solution().rob(intArrayOf(2,7,9,3,1)).print()
}