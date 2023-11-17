package com.blankj.hard._329

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import kotlin.math.max

class Solution {
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0
        val lengths = Array(matrix.size) { IntArray(matrix[0].size) }
        var res = Int.MIN_VALUE
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                val length = dfs(matrix, i, j, lengths)
                res = max(res, length)
            }
        }
        return res
    }

    private fun dfs(
        matrix: Array<IntArray>,
        i: Int, j: Int,
        lengths: Array<IntArray>
    ): Int {
        if (lengths[i][j] > 0) return lengths[i][j]
        val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, -1))
        var length = 1
        for (dir in dirs) {
            val row = i + dir[0]
            val col = j + dir[1]
            if (row in matrix.indices && col in matrix[0].indices
                && matrix[row][col] > matrix[i][j]) {
                val path = dfs(matrix, row, col, lengths)
                length = max(path + 1, length)
            }
        }
        lengths[i][j] = length
        return lengths[i][j]
    }
}

fun main() {
    Solution().longestIncreasingPath(
        MultiDimensionArray.createTestData(
            "[[1]]"
        )
    ).print()
    Solution().longestIncreasingPath(
        arrayOf(
            intArrayOf(9,9,4),
            intArrayOf(6,6,8),
            intArrayOf(2,1,1),
        )
    ).print()
    Solution().longestIncreasingPath(
        MultiDimensionArray.createTestData(
            "[[3,4,5],[3,2,6],[2,2,1]]"
        )
    ).print()
}