package com.blankj.medium._304

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray


class NumMatrix(matrix: Array<IntArray>) {

    val sum = Array(matrix.size + 1) { IntArray(matrix[0].size + 1) }

    init {

        for (i in matrix.indices) {
            var rowSum = 0
            for (j in matrix[0].indices) {
                rowSum += matrix[i][j]
                sum[i + 1][j + 1] = sum[i][j + 1] + rowSum
            }
        }
    }

    // 画图减去前缀和叠加部分， 注意左上角的点移动是因为考虑到矩阵第一行需要保留，
    // 因此整体左上角偏移了一格
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return sum[row2 + 1][col2 + 1] + sum[row1][col1] -
                sum[row2 + 1][col1] - sum[row1][col2 + 1]
    }

}

fun main() {
    NumMatrix(
        MultiDimensionArray.createTestData("[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]")
    ).sumRegion(2,1,4,3).print()
}
/**
 * Your NumMatrix object will be instantiated and called as such:
 * var obj = NumMatrix(matrix)
 * var param_1 = obj.sumRegion(row1,col1,row2,col2)
 */
