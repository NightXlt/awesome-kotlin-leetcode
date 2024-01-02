package com.blankj.easy._88


class Solution {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var index1 = m - 1
        var index2 = n - 1
        var cur = nums1.lastIndex
        while (cur > 0 && index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                nums1[cur--] = nums1[index1--]
            } else {
                nums1[cur--] = nums2[index2--]
            }
        }
        while (index2 >= 0) {
            nums1[cur--] = nums2[index2--]
        }
    }
}

fun main() {
    Solution().merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3)
}