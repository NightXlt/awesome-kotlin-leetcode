package com.blankj.medium._128

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val map = mutableMapOf<Int, Int>()
        var low: Int
        var high: Int
        var sum: Int
        var maxLength = 0
        for (num in nums) {
            if (map.contains(num)) continue
            low = map[num - 1] ?: 0
            high = map[num + 1] ?: 0
            sum = low + high + 1
            maxLength = max(sum, maxLength)
            map[num - low] = sum
            map[num + high] = sum
            map[num] = sum
        }
        return maxLength
    }

}

fun main() {
    Solution().longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2)).print()
    Solution().longestConsecutive(intArrayOf(1)).print()
    Solution().longestConsecutive(intArrayOf(1,5,4,2,3)).print()
}