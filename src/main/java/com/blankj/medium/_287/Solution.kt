package com.blankj.medium._287

import com.blankj.ext.print

class Solution {
    fun findDuplicate(nums: IntArray): Int {
        var slow = 0
        var fast = 0
        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while (slow != fast)
        slow = 0
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }
        return slow
    }
}

fun main() {
    Solution().findDuplicate(intArrayOf(1, 3, 4, 2, 2)).print()
}