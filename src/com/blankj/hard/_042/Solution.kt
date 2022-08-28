package com.blankj.hard._042

import com.blankj.ext.print

class Solution {
    var result = 0
    var maxValue = Int.MIN_VALUE

    fun trap(height: IntArray): Int {
        if (height.isEmpty() || height.size == 1) return 0
        val index = height.indices.maxByOrNull { height[it] }!!
        maxValue = Int.MIN_VALUE
        calculateDiff(height, 0 until index)
        maxValue = Int.MIN_VALUE
        calculateDiff(height, height.size - 1 downTo index + 1)
        return result
    }

    private fun calculateDiff(height: IntArray, range: Iterable<Int>) {
        for (i in range) {
            if (height[i] > maxValue) {
                maxValue = height[i]
                continue
            }
            result += maxValue - height[i]
        }
    }
}

fun main() {
    Solution().trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)).print()
    Solution().trap(intArrayOf(4, 2, 0, 3, 2, 5)).print()
}