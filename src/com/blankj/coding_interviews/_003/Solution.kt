package com.blankj.coding_interviews._003

class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        var i = 0
        while (i < nums.size) {
            if (nums[i] == i) {
                i++
                continue
            }
            if (nums[i] == nums[nums[i]]) return nums[i]
           nums[i] = nums[nums[i]].also { nums[nums[i]] = nums[i] }
        }
        return -1
    }
}

fun main() {
    println(Solution().findRepeatNumber(intArrayOf(2, 3, 1, 0, 2, 5, 3)))
}