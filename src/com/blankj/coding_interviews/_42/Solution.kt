package com.blankj.coding_interviews._42

import com.blankj.coding_interviews._004.print


class Solution {
    fun maxSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) throw IllegalArgumentException("empty array")
        var curSum = 0
        var maxSum = Int.MIN_VALUE
        for (num in nums) {
            if (curSum <= 0) {
                curSum = num
            } else {
                curSum += num
            }
            if (curSum > maxSum) {
                maxSum = curSum
            }
        }
        return maxSum
    }

    fun maxSubArrayDP(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        dp[0] = nums[0]
        var maxSum = dp[0]
        for (i in 1 until nums.size) {
            dp[i] = if (dp[i - 1] >= 0) dp[i - 1] + nums[i] else nums[i]
            maxSum = Math.max(dp[i], maxSum)
        }
        return maxSum
    }
}

fun main() {
    Solution().maxSubArray(intArrayOf(1, -2, 3, 10, -4, 7, 2, -5)).print()
    Solution().maxSubArray(intArrayOf(-2, -3, -10, -4, -5)).print()
    Solution().maxSubArray(intArrayOf(2, 3, 10, 4, 5)).print()
    Solution().maxSubArray(intArrayOf()).print()
}
