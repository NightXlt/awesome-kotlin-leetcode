package com.blankj.easy._485

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var count = 0
        for (num in nums) {
            if (num == 1) {
                count++
            } else {
                max = max(max, count)
                count = 0
            }
        }
        return max(max, count)
    }
}

fun main() {
    Solution().findMaxConsecutiveOnes(intArrayOf(1, 1, 0, 1, 1, 1)).print()
    Solution().findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 1)).print()
}