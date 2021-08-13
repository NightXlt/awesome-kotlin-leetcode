package com.blankj.medium.linkedin

import com.blankj.coding_interviews._004.print

class Solution3 {

    fun findTargetElement(
        nums: Array<IntArray>?,
        target: Int
    ): Boolean {
        if (nums.isNullOrEmpty()) return false
        var row = 0
        var col = nums[0].lastIndex
        while (row < nums.size && col > 0) {
            when {
                nums[row][col] == target -> return true
                nums[row][col] > target -> col--
                else -> row++
            }
        }
        return false
    }
}

fun main() {
    /**
     * test cases
     * 1. array = null; target = 3  return = false
     * 2. array = [
     *       1, 2, 3
     *       4, 5, 6
     *       7, 8, 9
     * ]; target = 5  return = true
     */

    Solution3().findTargetElement(null, 5).print()
    Solution3().findTargetElement(arrayOf(), 5).print()
    Solution3().findTargetElement(
        arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9),
        ),
        5
    ).print()
}