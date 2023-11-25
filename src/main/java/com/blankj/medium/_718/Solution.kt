package com.blankj.medium._718

import com.blankj.ext.print


class Solution {
    fun findLength(nums1: IntArray, nums2: IntArray): Int {
        val dp = Array(nums1.size + 1) { IntArray(nums2.size + 1) }
        var res = 0
        for (i in nums1.indices) {
            for (j in nums2.indices) {
                dp[i + 1][j + 1] = if (nums1[i] == nums2[j]) dp[i][j] + 1 else 0
                res = res.coerceAtLeast(dp[i + 1][j + 1])
            }
        }
        return res
    }
}

fun main() {
    Solution().findLength(intArrayOf(1, 2, 3, 2, 1), intArrayOf(3, 2, 1, 4, 7)).print()
}