package com.blankj.medium._377

import com.blankj.ext.print

class Solution {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1 // 空集排列为 1
        for (i in 1 .. target) {
            for (num in nums) {
                if (i >= num) {
                    dp[i] += dp[i - num]
                }
            }
        }
        return dp[target]
    }
}

fun main() {
    Solution().combinationSum4(intArrayOf(1, 2, 3), 4).print()
    Solution().combinationSum4(intArrayOf(9), 3).print()
}