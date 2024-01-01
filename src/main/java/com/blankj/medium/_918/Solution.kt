package com.blankj.medium._918

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxSubarraySumCircular(nums: IntArray): Int {
        var maxS = Int.MIN_VALUE // 最大子数组和，不能为空
        var minS = 0 // 最小子数组和，可以为空
        var maxF = 0
        var minF = 0
        var sum = 0
        for (x in nums) {
            // 以 nums[i-1] 结尾的子数组选或不选（取 max）+ x = 以 x 结尾的最大子数组和
            maxF = max(maxF, 0) + x
            maxS = max(maxS, maxF)
            // 以 nums[i-1] 结尾的子数组选或不选（取 min）+ x = 以 x 结尾的最小子数组和
            minF = min(minF, 0) + x
            minS = min(minS, minF)
            sum += x
        }
        return if (sum == minS) maxS else max(maxS, sum - minS)
    }

}

fun main() {
    Solution().maxSubarraySumCircular(intArrayOf(-9, -9, 1, 1, -9, -9)).print()
    Solution().maxSubarraySumCircular(intArrayOf(5, -3, 5)).print()
}