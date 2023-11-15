package com.blankj.easy._724


class Solution {
    fun pivotIndex(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        var total = nums.sum()
        var sum = 0
        for (i in nums.indices) {
            sum += nums[i]
            if (sum - nums[i] == total - sum) {
                return i
            }
        }
        return -1
    }
}