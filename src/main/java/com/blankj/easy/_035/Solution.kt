package com.blankj.easy._035

import com.blankj.ext.print

class Solution {
    // 当 left > right 时， left 所指向的一定是待插入的位置
    // 无论是 left =0, right = -1
    // left = length + 1, right = length, 亦或在中间的情况亦如此
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        // 取等号， 是判断当 left, right 指向同一个元素时，其大小与 target 还要再比一次
        while (left <= right) {
            val mid = (left + right) shr 1
            if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }
}

fun main() {
    Solution().searchInsert(intArrayOf(1,3,5,6), 5).print()
    Solution().searchInsert(intArrayOf(1,3,6), 5).print()
}