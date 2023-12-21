package com.blankj.medium._1004

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun longestOnes(nums: IntArray, k: Int): Int {
        var res = 0
        var left = 0
        var count = 0
        for (right in nums.indices) {
            count += 1 - nums[right]
            while (count > k) {
                count -= 1 - nums[left++]
            }
            res = max(res, right - left + 1)
        }
        return res
    }
}

fun main() {
    Solution().longestOnes(intArrayOf(1,1,1,0,0,0,1,1,1,1,0), 2).print()
}