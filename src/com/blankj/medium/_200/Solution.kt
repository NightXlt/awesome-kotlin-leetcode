package com.blankj.medium._200

import com.blankj.coding_interviews._004.print
import java.util.*

class Solution {
    var row = 0
    var col = 0
    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        var count = 0
        row = grid.size
        col = grid[0].size
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '0') continue
                count++
                grid[i][j] = '0'
                bfsClearNeighborsFlag(i, j, grid)
            }
        }
        return count
    }

    private fun bfsClearNeighborsFlag(i: Int, j: Int, grid: Array<CharArray>) {
        // store pair is also okay
        val neighbors = ArrayDeque<Int>()
        // multiply col rather than row
        neighbors.add(i * col + j)
        while (neighbors.isNotEmpty()) {
            val index = neighbors.pop()
            val iRow = index / col
            val iCol = index % col
            if (iRow - 1 >= 0 && grid[iRow - 1][iCol] == '1') {
                neighbors.add((iRow - 1) * col + iCol)
                grid[iRow - 1][iCol] = '0'
            }
            if (iRow + 1 < row && grid[iRow + 1][iCol] == '1') {
                neighbors.add((iRow + 1) * col + iCol)
                grid[iRow + 1][iCol] = '0'
            }
            if (iCol - 1 >= 0 && grid[iRow][iCol - 1] == '1') {
                neighbors.add(iRow * col + iCol - 1)
                grid[iRow][iCol - 1] = '0'
            }
            if (iCol + 1 < col && grid[iRow][iCol + 1] == '1') {
                neighbors.add(iRow * col + iCol + 1)
                grid[iRow][iCol + 1] = '0'
            }
        }
    }

    fun numIslandsDFS(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        var count = 0
        row = grid.size
        col = grid[0].size
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '0') continue
                count++
                grid[i][j] = '0'
                dfs(grid, i, j)
            }
        }
        return count
    }

    fun dfs(grid: Array<CharArray>, r: Int, c: Int) {
        val nr = grid.size
        val nc: Int = grid[0].size
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return
        }
        grid[r][c] = '0'
        dfs(grid, r - 1, c)
        dfs(grid, r + 1, c)
        dfs(grid, r, c - 1)
        dfs(grid, r, c + 1)
    }
}

fun main() {
    Solution().numIslands(arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0')
    )).print()
    Solution().numIslands(arrayOf(
            charArrayOf('1','1','1'),
            charArrayOf('0','1','0'),
            charArrayOf('1','1','1')
    )).print()

}
