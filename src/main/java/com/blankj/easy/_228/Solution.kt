package com.blankj.easy._228

import com.blankj.ext.print

class Solution {
    fun summaryRanges(nums: IntArray): List<String> {
        var i = 0
        val res = mutableListOf<String>()
        while (i < nums.size) {
            var j = i
            while (j < nums.lastIndex && nums[j] + 1 == nums[j + 1]) {
                j++
            }
            res.add(if (i == j) nums[i].toString() else "${nums[i]}->${nums[j]}")
            i = j + 1
        }
        return res
    }

}

fun main() {
    Solution().summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7)).print()
}