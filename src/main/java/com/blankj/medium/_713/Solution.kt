package com.blankj.medium._713

import com.blankj.ext.print


class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var count = 0
        var i = 0
        var product = 1
        for (j in nums.indices) {
            product *= nums[j]
            while (i <= j && product >= k) {
                product /= nums[i]
                i++
            }
            count += if (j >= i) (j - i + 1) else 0
        }
        return count
    }
}

fun main() {
    Solution().numSubarrayProductLessThanK(intArrayOf(10, 5, 2, 6), 100).print()
    Solution().numSubarrayProductLessThanK(intArrayOf(1, 2, 3), 0).print()
}