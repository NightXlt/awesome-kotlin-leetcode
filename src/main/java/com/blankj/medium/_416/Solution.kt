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
        for (num in nums) {
            for (j in target downTo num) {
                // 正向迭代下面的 dp 一定会有问题, 所以上面才是倒着来写的
                // 正向迭代意味着每个数字可以重复取, 而倒序则意味着每个数字只能取 0,1 次.
                if (!dp[j]) {
                    // 留意啊, (j - nums[i - 1]) 一定是在 j 的左侧, j 左侧的元素是上一行的值, 对应的是二维数组的 dp[i - 1][j - nums[i -1]]
                    dp[j] = dp[j - num]
                }
            }
        }
        return dp[target]
    }

    private fun subsetSumWithMoreSpace(nums: IntArray, target: Int): Boolean {
        val dp = Array(nums.size + 1) { BooleanArray(target + 1) }
        repeat(nums.size) { i -> dp[i][0] = true }
        for (i in 1..nums.size) {
            for (j in nums[i - 1]..target) {
                dp[i][j] = dp[i - 1][j] // 模拟不放第 i - 1 个元素的情况
                if (!dp[i][j]) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] // 模拟放第 i - 1 个元素
                }
            }
        }
        return dp[nums.size][target]
    }

}

fun main() {
    Solution().canPartition(intArrayOf(1, 5, 11, 5)).print()
    Solution().canPartition(intArrayOf(1, 2, 3, 5)).print()
}