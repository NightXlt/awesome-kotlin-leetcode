package com.blankj.medium._560

import com.blankj.coding_interviews._004.print

class Solution {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        var sum = 0
        val map = mutableMapOf(0 to 1)
        for (num in nums) {
            sum += num
            if (map.containsKey(sum - k)) {
                count += map[sum - k]!!
            }
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
        return count
    }
}

fun main() {
    Solution().subarraySum(
            intArrayOf(1, 1, 1),
            2
    ).print()
}