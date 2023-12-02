package com.blankj.hard._980

class Solution {
    private var result = 0
    private var end = IntArray(2)
    private var rest = 0
    private val dirs = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
    )
    fun uniquePathsIII(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        // pay attention is col equals in each rows
        rest = grid.size * grid.first().size
        var start = IntArray(2)
        for (i in grid.indices) {
            for (j in grid.first().indices) {
                when (grid[i][j]) {
                    -1 -> rest--
                    1 -> {
                        start[0] = i
                        start[1] = j
                    }
                    2 -> {
                        end[0] = i
                        end[1] = j
                    }
                }
            }
        }
        dfs(grid, start[0], start[1])
        return result
    }

    private fun dfs(grid: Array<IntArray>, row: Int, col: Int) {
        if (row == end[0] && col == end[1]) {
            if (rest == 1) result++
            return
        }
        if (rest == 1) return
        rest--
        grid[row][col] = -1
        for (dir in dirs) {
            var newRow = row + dir[0]
            var newCol = col + dir[1]
            if (newRow in grid.indices && newCol in grid.first().indices && grid[newRow][newCol] != -1) {
                dfs(grid, newRow, newCol)
            }
        }
        grid[row][col] = 0
        rest++
    }
}

fun main() {

}