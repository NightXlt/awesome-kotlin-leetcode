package com.blankj.coding_interviews._61

import com.blankj.coding_interviews._004.print
import java.util.Arrays.sort

class Solution {
    fun isStraight(nums: IntArray): Boolean {
        if (nums.isEmpty() || nums.size < 5) return false
        sort(nums)
        val countOfZero = nums.count { it == 0 }
        var countOfGap = 0
        var small = countOfZero // 从非 0处开始访问，牌不会比 0 小
        var big = small + 1
        while (big < nums.size) {
            if (nums[small] == nums[big]) return false
            countOfGap += nums[big] - nums[small] - 1
            small = big
            big++
        }
        return countOfGap <= countOfZero
    }
}

fun main() {
    Solution().isStraight(intArrayOf(1, 2, 3, 4, 5)).print()
    Solution().isStraight(intArrayOf(0, 0, 1, 2, 5)).print()
}