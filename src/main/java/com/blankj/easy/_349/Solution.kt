package com.blankj.easy._349


class Solution {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        if (nums1.size > nums2.size) return intersection(nums2, nums1)
        val freq = mutableMapOf<Int, Boolean>()
        nums1.forEach {
            freq[it] = true
        }
        val res = mutableSetOf<Int>()
        for (num in nums2) {
            if (num in freq) {
                res.add(num)
            }
        }
        return res.toIntArray()
    }
}