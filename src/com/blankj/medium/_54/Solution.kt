package com.blankj.medium._54

import com.blankj.ext.print

class Solution {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val res = mutableListOf<Int>()
        if (matrix.isEmpty()) return res
        var up = 0
        var left = 0
        var right = matrix[0].lastIndex
        var down = matrix.lastIndex
        while (true) {
            for (i in left..right) {
                res.add(matrix[up][i])
            }
            if (++up > down) break
            for (i in up..down) {
                res.add(matrix[i][right])
            }
            if (--right < left) break
            for (i in right downTo left) {
                res.add(matrix[down][i])
            }
            if (--down < up) break
            for (i in down downTo up) {
                res.add(matrix[i][left])
            }
            if (++left > right) break
        }
        return res
    }
}

fun main() {
    Solution().spiralOrder(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))).print()
    Solution().spiralOrder(arrayOf()).print()
}