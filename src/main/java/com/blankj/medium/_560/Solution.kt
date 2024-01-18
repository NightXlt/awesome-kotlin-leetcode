package com.blankj.medium._560

import com.blankj.ext.print

class Solution {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        var sum = 0
        // key: sum   value: count
        val map = mutableMapOf(0 to 1)
        for (num in nums) {
            sum += num
            if (map.containsKey(sum - k)) {
                count += map[sum - k]!!
            }
            map.merge(sum, 1, Integer::sum)
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
