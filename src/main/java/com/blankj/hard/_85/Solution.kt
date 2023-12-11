package com.blankj.hard._85

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0
        var res = 0
        val heights = IntArray(matrix[0].size)
        for (row in matrix) {
            for (i in row.indices) {
                if (row[i] == '0') {
                    heights[i] = 0
                } else {
                    heights[i]++
                }
            }
            res = max(res, largestRectangleArea(heights))
        }
        return res
    }

    private fun largestRectangleArea(heights: IntArray): Int {
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
    Solution().maximalRectangle(
        arrayOf(
            charArrayOf('1','0','1','0','0'),
            charArrayOf('1','0','1','1','1'),
            charArrayOf('1','1','1','1','1'),
            charArrayOf('1','0','0','1','0'),
        )
    ).print()
}