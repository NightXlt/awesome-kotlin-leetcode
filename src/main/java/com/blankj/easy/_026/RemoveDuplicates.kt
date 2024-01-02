package com.blankj.easy._026

class RemoveDuplicates {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var length = 1
        var slow = 0
        for (fast in nums.indices) {
            if (nums[slow] != nums[fast]) {
                slow++
                nums[slow] = nums[fast]
                length++
            }
        }
        return length
    }
}