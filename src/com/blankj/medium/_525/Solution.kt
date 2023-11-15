package com.blankj.medium._525

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun findMaxLength(nums: IntArray): Int {
        var sum = 0
        val map = mutableMapOf(0 to -1)
        var res = Int.MIN_VALUE
        for ((i, num) in nums.withIndex()) {
            sum += if (num == 0) -1 else 1
            if (map.contains(num)) {
                res = max(res, i - map.getValue(num))
            }
            map[sum] = i
        }
        return res
    }
}

fun main() {
    Solution().findMaxLength(intArrayOf(1, 0)).print()
    Solution().findMaxLength(intArrayOf(0, 1, 0)).print()
}