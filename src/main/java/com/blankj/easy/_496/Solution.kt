package com.blankj.easy._496

class Solution {
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        if (nums2.isEmpty()) return intArrayOf()
        val map = mutableMapOf<Int, Int>()
        val stack = ArrayDeque<Int>()
        for (i in nums2.indices.reversed()) {
            while (stack.isNotEmpty() && nums2[i] > nums2[stack.last()]) {
                stack.removeLast()
            }
            map[nums2[i]] = if (stack.isNotEmpty()) nums2[stack.last()] else -1
            stack.add(i)
        }
        val res = IntArray(nums1.size)
        for (i in nums1.indices) {
            res[i] = map.getOrDefault(nums1[i], -1)
        }
        return res
    }
}