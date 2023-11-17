package com.blankj.medium._695

import com.blankj.ext.print
import kotlin.math.max


class Solution {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val visited = Array(rows) { BooleanArray(cols) }
        var maxArea = 0
        repeat(rows) { i ->
            repeat(cols) { j ->
                if (grid[i][j] == 1 && !visited[i][j]) {
                    val area = getArea(grid, visited, i, j)
                    maxArea = max(area, maxArea)
                }

            }
        }
        return maxArea
    }

    private fun getArea(
        grid: Array<IntArray>,
        visited: Array<BooleanArray>,
        row: Int, col: Int
    ): Int {
        val queue = ArrayDeque<IntArray>()
        queue.add(intArrayOf(row, col))
        visited[row][col] = true
        var area = 0
        val dirs = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
        )
        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            area++
            for (dir in dirs) {
                val r = pos[0] + dir[0]
                val c = pos[1] + dir[1]
                if (r in grid.indices && c in grid[0].indices
                    && grid[r][c] == 1 && !visited[r][c]) {
                    queue.add(intArrayOf(r, c))
                    visited[r][c] = true
                }
            }
        }
        return area
    }
}

fun main() {
    Solution().maxAreaOfIsland(
        arrayOf(
            intArrayOf(1),
        )
    ).print()
    Solution().maxAreaOfIsland(
        arrayOf(
            intArrayOf(0,0,1,0,0,0,0,1,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
            intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,0,0,0,0),
        )
    ).print()
    Solution().maxAreaOfIsland(
        arrayOf(
            intArrayOf(0,0,0,0,0,0,0,0),
        )
    ).print()
}