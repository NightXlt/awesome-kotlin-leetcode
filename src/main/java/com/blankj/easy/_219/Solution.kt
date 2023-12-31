package com.blankj.easy._219

import com.blankj.ext.print

class Solution {
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = mutableMapOf<Int, ArrayDeque<Int>>()
        for ((i, num) in nums.withIndex()) {
            if (num in map && i - map.getValue(num).last() <= k) {
                return true
            }
            map.getOrPut(num) { ArrayDeque() }.add(i)
        }
        return false
    }

    fun containsNearbyDuplicateWithSet(nums: IntArray, k: Int): Boolean {
        // sliding window
        val set = mutableSetOf<Int>()
        for ((i, num) in nums.withIndex()) {
            if (set.size > k) set.remove(nums[i - k - 1])
            if (!set.add(num)) return true
        }
        return false
    }
}

fun main() {
    Solution().containsNearbyDuplicate(intArrayOf(1, 2, 3, 1), 3).print()
    Solution().containsNearbyDuplicate(intArrayOf(1, 0, 1, 1), 1).print()
    Solution().containsNearbyDuplicate(intArrayOf(1, 2, 3, 1, 2, 3), 2).print()
}