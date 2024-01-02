package com.blankj.easy._27


class Solution {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var deleteNum = 0
        for (i in nums.indices) {
            if (deleteNum > 0) {
                nums[i - deleteNum] = nums[i]
            }
            if (nums[i] == `val`) {
                deleteNum++
            }
        }
        for (i in nums.size - deleteNum..<nums.size) {
            nums[i] = 0
        }
        return nums.size - deleteNum
    }
}