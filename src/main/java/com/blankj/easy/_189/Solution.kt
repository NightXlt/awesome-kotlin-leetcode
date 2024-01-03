package com.blankj.easy._189

class Solution {
    fun rotate(nums: IntArray, k: Int): Unit {
        if (nums.isEmpty()) return
        val k = k % nums.size
        if (k == 0) return
        val temp = IntArray(nums.size)
        for (i in 0..<k) {
            temp[i] = nums[nums.size - k + i]
        }
        for (i in k..<nums.size) {
            temp[i] = nums[i - k]
        }
        for (i in nums.indices) {
            nums[i] = temp[i]
        }
    }
}

fun main() {
    Solution().rotate(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3)
}