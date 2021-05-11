package com.blankj.medium._048


class Solution {
    fun rotate(matrix: Array<IntArray>?) {
        if (matrix == null || matrix.size < 2) return
        for (i in matrix.indices)
            for (j in 0 until i)
                matrix[i][j] = matrix[j][i].also {
                    matrix[j][i] = matrix[i][j]
                }

        val n = matrix.size
        for (i in matrix.indices) {
            for (j in 0 until n / 2)
                matrix[i][j] = matrix[i][n - j - 1].also {
                    matrix[i][n - j - 1] = matrix[i][j]
                }
        }
    }

}

fun main() {
    val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
    )
    Solution().rotate(matrix)
    matrix.forEach {
        it.forEach(::print)
        println()
    }
}