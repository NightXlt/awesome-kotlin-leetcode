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

    private fun dfs(x1: Int, y1: Int, x2: Int, y2: Int): Node? {
        val curSum = sum[x2 + 1][y2 + 1] + sum[x1][y1] - sum[x2 + 1][y1] - sum[x1][y2 + 1]
        val dx = x2 - x1 + 1
        val dy = y2 - y1 + 1
        val total = dx * dy
        if (curSum == 0 || curSum == total) {
            return Node(curSum == total, true)
        }
        val node = Node(g[x1][y1] == 0, false)
        node.topLeft = dfs(x1, y1, x1 + dx / 2 - 1, y1 + dy / 2 - 1)
        node.topRight = dfs(x1, y1 + dy / 2, x1 + dx / 2 - 1, y2)
        node.bottomLeft = dfs(x1 + dx / 2, y1, x2, y1 + dy / 2 - 1)
        node.bottomRight = dfs(x1 + dx / 2, y1 + dy / 2, x2, y2)
        return node
    }
}

fun main() {
    Solution().construct(
        MultiDimensionArray.createTestData("[[0,1],[1,0]]")
    )?.print()
}