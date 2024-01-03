package com.blankj.medium._238

import com.blankj.structure.TreeNode
import kotlin.math.min


class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        if (nums.isEmpty()) return nums
        val res = IntArray(nums.size)
        res[0] = 1
        for (i in 1..<nums.size) {
            res[i] = res[i - 1] * nums[i - 1]
        }
        var rightMul = 1
        for (i in nums.indices.reversed()) {
            res[i] *= rightMul
            rightMul *= nums[i]
        }
        return res
    }
}