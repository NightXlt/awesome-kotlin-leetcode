package com.blankj.medium._463

import com.blankj.coding_interviews._004.print
import java.util.*

class Solution {

    fun islandPerimeter(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j)
                }
            }
        }
        return 0
    }

    private fun dfs(grid: Array<IntArray>, i: Int, j: Int): Int {
        if (!isInArea(grid, i, j) || grid[i][j] == 0) return 1
        if (grid[i][j] != 1) return 0
        grid[i][j] = 2
        return dfs(grid, i - 1, j) +
                dfs(grid, i + 1, j) +
                dfs(grid, i, j - 1) +
                dfs(grid, i, j + 1)
    }

    private fun isInArea(grid: Array<IntArray>, i: Int, j: Int): Boolean {
        return i in grid.indices && j in grid[0].indices
    }
}

fun main() {
    Solution().islandPerimeter(arrayOf()).print()
    Solution().islandPerimeter(arrayOf(intArrayOf())).print()
    Solution().islandPerimeter(arrayOf(
            intArrayOf(0,1,0,0),
            intArrayOf(1,1,1,0),
            intArrayOf(0,1,0,0),
            intArrayOf(1,1,0,0)
    )).print()
}
