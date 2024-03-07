package com.blankj.medium._2006


class Solution {
    fun countKDifference(nums: IntArray, k: Int): Int {
        val map = mutableMapOf<Int, Int>()
        var res = 0
        for (i in nums.indices) {
            res += map.getOrDefault(nums[i] - k, 0)
            res += map.getOrDefault(nums[i] + k, 0)
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
        }
        return res
    }
}