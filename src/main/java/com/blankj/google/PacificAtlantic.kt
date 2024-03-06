package com.blankj.google

// T: O(M*N)    S: O(M*N)
class PacificAtlantic {


    lateinit var heights: Array<IntArray>
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        if (heights.isEmpty()) return emptyList()
        this.heights = heights
        val result = mutableListOf<List<Int>>()
        val m = heights.size
        val n = heights.first().size
        val pacific = Array(m) { BooleanArray(n) }
        val atlantic = Array(m) { BooleanArray(n) }
        for (i in heights.indices) {
            dfs(i, 0, pacific)
            dfs(i, n - 1, atlantic)
        }
        for (i in heights.first().indices) {
            dfs(0, i, pacific)
            dfs(m - 1, i, atlantic)
        }
        for (i in heights.indices) {
            for (j in heights.first().indices) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(listOf(i, j))
                }
            }
        }
        return result
    }

    private val dirs = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
        intArrayOf(1, 0),
    )

    private fun dfs(row: Int, col: Int, ocean: Array<BooleanArray>) {
        if (ocean[row][col]) return
        ocean[row][col] = true
        for ((rowDiff, colDiff) in dirs) {
            val newRow = row + rowDiff
            val newCol = col + colDiff
            if (newRow in ocean.indices && newCol in ocean.first().indices && heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, ocean)
            }
        }
    }
}