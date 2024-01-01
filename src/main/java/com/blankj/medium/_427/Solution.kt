package com.blankj.medium._427

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Node(var `val`: Boolean, var isLeaf: Boolean) {
    var topLeft: Node? = null
    var topRight: Node? = null
    var bottomLeft: Node? = null
    var bottomRight: Node? = null
}


class Solution {

    private val sum = Array(70) { IntArray(70) }

    private var g: Array<IntArray> = arrayOf()
    fun construct(grid: Array<IntArray>): Node? {
        if (grid.isEmpty()) return null
        for (i in grid.indices) {
            var rowSum = 0
            for (j in grid[0].indices) {
                rowSum += grid[i][j]
                sum[i + 1][j + 1] = sum[i][j + 1] + rowSum
            }
        }
        this.g = grid
        return dfs(0, 0, grid.lastIndex, grid.lastIndex)
    }

    private fun dfs(row1: Int, col1: Int, row2: Int, col2: Int): Node? {
        val curSum = sum[row2 + 1][col2 + 1] + sum[row1][col1] - sum[row2 + 1][col1] - sum[row1][col2 + 1]
        val dx = row2 - row1 + 1
        val dy = col2 - col1 + 1
        val total = dx * dy
        if (curSum == 0 || curSum == total) {
            return Node(curSum == total, true)
        }
        val node = Node(g[row1][col1] == 0, false)
        node.topLeft = dfs(row1, col1, row1 + dx / 2 - 1, col1 + dy / 2 - 1)
        node.topRight = dfs(row1, col1 + dy / 2, row1 + dx / 2 - 1, col2)
        node.bottomLeft = dfs(row1 + dx / 2, col1, row2, col1 + dy / 2 - 1)
        node.bottomRight = dfs(row1 + dx / 2, col1 + dy / 2, row2, col2)
        return node
    }
}

fun main() {
    Solution().construct(
        MultiDimensionArray.createTestData("[[0,1],[1,0]]")
    )?.print()
}