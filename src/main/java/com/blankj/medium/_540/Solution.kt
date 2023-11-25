package com.blankj.medium._540

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun singleNonDuplicate(nums: IntArray): Int {
        var left = 0
        var right = nums.size shr 1
        while (left <= right) {
            val mid = (left + right) shr 1
            val i = mid * 2

            if (i < nums.size - 1 && nums[i] != nums[i + 1]) {
                // 如果 mid = 0 的话那势必是第一（i）个元素是非重复了
                // mid != 0 时， i 至少 >= 2, 所以这里的 - 是安全的
                if (mid == 0 || nums[i - 2] == nums[i - 1]) return nums[i]
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return nums.last()
    }
}

fun main() {
    Solution().singleNonDuplicate(intArrayOf(1, 1, 2, 2, 3)).print()
    Solution().singleNonDuplicate(intArrayOf(1, 2, 2, 3, 3)).print()
    Solution().singleNonDuplicate(intArrayOf(1, 1, 2, 3, 3)).print()

}