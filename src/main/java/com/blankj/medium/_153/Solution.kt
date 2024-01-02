package com.blankj.medium._153

import com.blankj.ext.print

class Solution {
    fun findMin(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        if (nums.first() < nums.last()) return nums.first()
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = (left + right) shr 1
            when {
                nums[mid] > nums[right] -> left = mid + 1
                nums[mid] < nums[left] -> right = mid
                else -> right--
            }
        }
        return nums[left]
    }
}

fun main() {
    Solution().findMin(intArrayOf(4, 5, 6, 7, 0, 1, 2)).print()
    Solution().findMin(intArrayOf(3, 4, 5, 1, 2)).print()
}