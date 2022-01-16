package com.blankj.medium._75

import com.blankj.ext.print

class Solution {
    fun sortColors(nums: IntArray): Unit {
        if (nums.isEmpty()) return
        var p0 = 0
        var p2 = nums.lastIndex
        var i = 0
        while (i <= p2) {
            // Assure nums[i] != 2, move 2 to p2
            while (i <= p2 && nums[i] == 2) {
                nums[i] = nums[p2].also { nums[p2] = nums[i] }
                p2--
            }
            if (nums[i] == 0) {
                nums[i] = nums[p0].also { nums[p0] = nums[i] }
                p0++
            }
            i++
        }
    }
}

fun main() {
    var nums = intArrayOf(2, 0, 2, 1, 1, 0)
    Solution().sortColors(nums)
    nums.print()
    nums = intArrayOf(1, 0, 2)
    Solution().sortColors(nums)
    nums.print()
}
