package com.blankj.hard._042

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    var result = 0
    var maxValue = Int.MIN_VALUE

    fun trap(height: IntArray): Int {
        if (height.isEmpty() || height.size == 1) return 0
        maxValue = Int.MIN_VALUE
        var index = 0
        for ((i, value) in height.withIndex()) {
            if (value > maxValue) {
                maxValue = value
                index = i
            }
        }
        maxValue = Int.MIN_VALUE
        for (i in 0 until index) {
            calculateDiff(height, i)
        }
        maxValue = Int.MIN_VALUE

        for (i in height.size - 1 downTo index + 1) {
            calculateDiff(height, i)
        }
        return result
    }

    private fun calculateDiff(height: IntArray, i: Int) {
        if (height[i] > maxValue) {
            maxValue = height[i]
            return
        }
        result += maxValue - height[i]
    }
}

fun main() {
    Solution().trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)).print()
    Solution().trap(intArrayOf(4, 2, 0, 3, 2, 5)).print()
}