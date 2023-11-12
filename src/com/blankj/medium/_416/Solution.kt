package com.blankj.medium._416

import com.blankj.ext.print

class Solution {
    fun canPartition(nums: IntArray): Boolean {
        if (nums.isEmpty()) return true
        val sum = nums.sum()
        if (sum % 2 == 1) {
            return false
        }
        return subsetSum(nums, sum / 2)
    }

    private fun subsetSum(nums: IntArray, target: Int): Boolean {
        val dp = BooleanArray(target + 1)
        dp[0] = true
        for (i in 1 until nums.size) {
            for (j in target downTo nums[i - 1]) {
                if (!dp[j]) {
                    dp[j] = dp[j - nums[i - 1]]
                }
            }
        }
        return dp[target]
    }
}

fun main() {
    Solution().canPartition(intArrayOf(1,5,11,5)).print()
    Solution().canPartition(intArrayOf(1,2,3,5)).print()
}