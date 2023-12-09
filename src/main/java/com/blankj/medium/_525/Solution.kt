package com.blankj.medium._525

import com.blankj.ext.print
import kotlin.math.max

class Solution {

    fun findMaxLength(nums: IntArray): Int {
        var sum = 0
        val map = mutableMapOf(0 to -1)
        var res = 0
        for ((i, num) in nums.withIndex()) {
            sum += if (num == 0) -1 else 1
            if (map.contains(sum)) {
                res = max(res, i - map.getValue(sum))
            } else {
                map[sum] = i
            }
        }
        return res
    }
}

fun main() {
    Solution().findMaxLength(intArrayOf(1, 0)).print()
    Solution().findMaxLength(intArrayOf(0, 1, 0)).print()
}