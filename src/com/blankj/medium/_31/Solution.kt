package com.blankj.medium._31

import com.blankj.coding_interviews._004.print

class Solution {
    fun nextPermutation(nums: IntArray) {
        var i = nums.size - 2
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--
        }
        if (i >= 0) {
            var j = nums.lastIndex
            while (j >= 0 && nums[i] >= nums[j]) {
                j--
            }
            nums[i] = nums[j].also { nums[j] = nums[i] }
        }
        reverse(nums, i + 1)
    }

    private fun reverse(nums: IntArray, start: Int) {
        var (start, end) = start to nums.lastIndex
        while (start < end) {
            nums[start] = nums[end].also { nums[end] = nums[start] }
            start++
            end--
        }
    }
}

fun main() {
    var nums = intArrayOf(1, 2, 3)
    Solution().nextPermutation(
            nums
    )
    nums.print()
    nums = intArrayOf(3, 2, 1)
    Solution().nextPermutation(
            nums
    )
    nums.print()
    nums = intArrayOf(1, 1, 5)
    Solution().nextPermutation(
            nums
    )
    nums.print()
    nums = intArrayOf(4, 5, 3, 6, 2, 1)
    Solution().nextPermutation(
            nums
    )
    nums.print()
}
