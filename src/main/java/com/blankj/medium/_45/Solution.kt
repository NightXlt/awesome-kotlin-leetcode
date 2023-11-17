package com.blankj.medium._45

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun jump(nums: IntArray): Int {
        var end = 0
        var maxPosition = 0
        var steps = 0
        // 数组最后一个元素不要访问了, 如果恰好最后一个元素是 end, 会额外加一个不必要的跳转
        for (i in 0 until nums.lastIndex) {
            maxPosition = max(maxPosition, i + nums[i])
            if (i == end) {
                end = maxPosition
                steps++
            }
        }
        return steps
    }
}

fun main() {
    Solution().jump(intArrayOf(2,3,1,1,4)).print()
    Solution().jump(intArrayOf(2,3,0,1,4)).print()
}