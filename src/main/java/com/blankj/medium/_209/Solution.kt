package com.blankj.medium._209

import kotlin.math.min


class Solution {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        if (nums.isEmpty()) error("Empty array!")
        var minLength = Int.MAX_VALUE
        var i = 0
        var sum = 0
        for (j in nums.indices) {
            sum += nums[j]
            while (i <= j && sum >= target) {
                minLength = min(j - i + 1, minLength)
                sum -= nums[i]
                i++
            }
        }
        return if (minLength == Int.MAX_VALUE) 0 else minLength
    }
}