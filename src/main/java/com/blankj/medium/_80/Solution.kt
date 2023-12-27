package com.blankj.medium._80

import com.blankj.ext.print

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size <= 2) return nums.size
        var slow = 2
        var fast = 2
        while (fast < nums.size) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast]
                slow++
            }
            fast++
        }
        return slow
    }
}

fun main() {
    Solution().removeDuplicates(intArrayOf(1, 1, 1, 2, 2, 3)).print()
}