package com.blankj.easy._169


class Solution {

    fun majorityElement(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        var majorityElement = nums[0]
        var count = 1
        for (i in 1..<nums.size) {
            if (nums[i] == majorityElement) {
                count++
            } else {
                count--
                if (count == 0) {
                    majorityElement = nums[i]
                    count = 1
                }
            }
        }
        return majorityElement
    }
}