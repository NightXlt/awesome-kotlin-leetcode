package com.blankj.medium._073

import com.blankj.coding_interviews._004.print


class Solution {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return
        var flagRowZero = false
        var flagColZero = false
        flagRowZero = matrix[0].contains(0)
        matrix.forEach {
            if (it[0] == 0) {
                flagColZero = true
            }
        }
        for (i in 1..matrix.lastIndex) {
            for (j in 1..matrix[0].lastIndex) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }
        for (i in 1..matrix.lastIndex) {
            for (j in 1..matrix[0].lastIndex) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }

        if (flagRowZero) {
            for (i in matrix[0].indices) {
                matrix[0][i] = 0
            }
        }
        if (flagColZero) {
            for (i in matrix.indices) {
                matrix[i][0] = 0
            }
        }

    }
}

fun main() {
    //[1,1,1],[1,0,1],[1,1,1]
    val matrix = arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 1),
            intArrayOf(1, 1, 1)
    )
    Solution().setZeroes(matrix)
    matrix.print()
}