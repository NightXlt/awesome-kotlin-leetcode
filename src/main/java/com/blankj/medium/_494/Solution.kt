package com.blankj.medium._494

class Solution {

    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        val diff = sum - target
        if (diff < 0 || diff % 2 != 0) return 0
        val neg = diff / 2
        val dp = IntArray(neg + 1)
        dp[0] = 1
        for (num in nums) {
            for (j in neg downTo num) {
                dp[j] += dp[j - num]
            }
        }
        return dp[neg]
    }

}

fun main() {
    val nums = intArrayOf(
        1, 2, 3, 4
    )
    Solution().findTargetSumWays(nums, 2)
}