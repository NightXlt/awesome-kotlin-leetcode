package com.blankj.coding_interviews._019

import com.blankj.coding_interviews._004.print

class Solution {

    fun exchange(nums: IntArray, predicate: (num: Int) -> Boolean): IntArray {
        if (nums.isEmpty()) return nums
        var left = 0
        var right = nums.lastIndex
        while (left < right) {
            while (left < right && predicate(nums[left])) {
                left++
            }
            while (left < right && !predicate(nums[right])) {
                right--
            }
            if (left < right) {
                nums[left] = nums[right].also { nums[right] = nums[left] }
            }
        }
        return nums
    }

    fun exchange(nums: IntArray): IntArray {
        if (nums.isEmpty()) return nums
        var left = 0
        var right = nums.lastIndex
        while (left < right) {
            while (left < right && nums[left] and 0x01 == 0x01) {
                left++
            }
            while (left < right && nums[right] and 0x01 == 0) {
                right--
            }
            if (left < right) {
                nums[left] = nums[right].also { nums[right] = nums[left] }
            }
        }
        return nums
    }
}


fun main() {
    Solution().exchange(intArrayOf()).print()
    Solution().exchange(intArrayOf(2, 3, 4, 5, 6)).print()
    Solution().exchange(intArrayOf(2, 4, 6, 3, 5)).print()
    Solution().exchange(intArrayOf(3, 5, 2, 4, 6)).print()
    Solution().exchange(intArrayOf(2, 3, 4, 5, 6), ::oddPrior).print()
}

fun oddPrior(num: Int): Boolean {
    return num and 0x01 == 0x01
}