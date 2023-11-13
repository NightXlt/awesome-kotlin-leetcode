package com.blankj.hard._84

import com.blankj.ext.print
import com.blankj.structure.ListNode
import kotlin.math.max

class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
        val stack = ArrayDeque<Int>()
        stack.add(-1)
        // 计算以每个柱子为顶的矩形面积
        var maxArea = 0
        for (i in heights.indices) {
            while (stack.last() != -1 && heights[stack.last()] >= heights[i]) {
                val height = heights[stack.removeLast()]
                val width = i - stack.last() - 1
                maxArea = max(maxArea, height * width)
            }
            stack.add(i)
        }
        while (stack.last() != -1) {
            val height = heights[stack.removeLast()]
            val width = heights.size - stack.last() - 1
            maxArea = max(maxArea, height * width)
        }
        return maxArea
    }
}

fun main() {
    Solution().largestRectangleArea(intArrayOf(3, 2, 5, 4, 6, 1, 4, 2)).print()
    Solution().largestRectangleArea(intArrayOf(5, 4)).print()
}