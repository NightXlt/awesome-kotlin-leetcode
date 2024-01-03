package com.blankj.medium._011

import kotlin.math.min

class MaxArea {
    fun maxArea(height: IntArray): Int {
        var maxArea = Int.MIN_VALUE
        var low = 0
        var high = height.lastIndex
        while (low < high) {
            maxArea = maxArea.coerceAtLeast(
                min(height[high], height[low]) * (high - low)
            )
            if (height[low] <= height[high]) {
                low++
            } else {
                high--
            }
        }
        return maxArea
    }
}