package com.blankj.medium._503

class Solution {
    fun nextGreaterElements(nums: IntArray): IntArray {
        if (nums.isEmpty()) return intArrayOf()
        val res = IntArray(nums.size) { -1 }
        val n = nums.size
        val stack = ArrayDeque<Int>()
        for (i in 0..<2 * nums.size) {
            while (stack.isNotEmpty() && nums[i % n] > nums[stack.last()]) {
                val cur = stack.removeLast()
                res[cur] = nums[i % n]
            }
            stack.add(i % n)
        }
        return res
    }
}