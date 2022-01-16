package com.blankj.coding_interviews._29

import com.blankj.ext.print


class Solution {
    fun spiralOrder(matrix: Array<IntArray>): IntArray {
        if (matrix.isEmpty()) {
            return intArrayOf()
        }
        val result = IntArray(size = matrix.size * matrix[0].size)
        var up = 0
        var down = matrix.size - 1
        var left = 0
        var right: Int = matrix[0].size - 1
        var index = 0
        while (true) {
            for (j in left..right) {
                result[index++] = matrix[up][j]
            }
            if (++up > down) break
            for (i in up..down) {
                result[index++] = matrix[i][right]
            }
            if (--right < left) break
            for (j in right downTo left) {
                result[index++] = matrix[down][j]
            }
            if (--down < up) break
            for (i in down downTo up) {
                result[index++] = matrix[i][left]
            }
            if (++left > right) break
        }
        return result
    }
}

fun main() {
    Solution().spiralOrder(arrayOf()).print()
    Solution().spiralOrder(arrayOf(intArrayOf())).print()
    Solution().spiralOrder(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
    )).print()
    Solution().spiralOrder(arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8),
            intArrayOf(9, 10, 11, 12)
    )).print()
}
