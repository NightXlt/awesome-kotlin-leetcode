package com.blankj.medium._1254

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray


class Solution {
    fun closedIsland(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val row = grid.size
        val col = grid[0].size
        var res = 0
        val dirs = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
        )
        for (i in 0 until row) {
            for (j in 0 until col) {
                // 已访问过就不重复访问
                if (grid[i][j] == 1) continue
                grid[i][j] = 1
                var closed = true
                val queue = ArrayDeque<IntArray>()
                queue.add(intArrayOf(i , j))
                while (queue.isNotEmpty()) {
                    val (curRow, curCol) = queue.removeFirst()
                    if (curRow == 0 || curRow == row - 1 || curCol == 0 || curCol == col - 1) {
                        closed = false
                        break
                    }
                    for (dir in dirs) {
                        val nextRow = curRow + dir[0]
                        val nextCol = curCol + dir[1]
                        if (nextRow in grid.indices && nextCol in grid[0].indices && grid[nextRow][nextCol] == 0) {
                            grid[nextRow][nextCol] = 1
                            queue.add(intArrayOf(nextRow, nextCol))
                        }
                    }
                }
                if (closed) {
                    res++
                }
            }
        }
        return res
    }
}

fun main() {
    Solution().closedIsland(
        MultiDimensionArray.createTestData("[[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]")
    ).print()
}