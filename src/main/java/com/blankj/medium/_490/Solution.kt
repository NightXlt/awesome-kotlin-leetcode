package com.blankj.medium._490

class Solution {
    fun hasPath(
        maze: Array<IntArray>,
        start: IntArray,
        destination: IntArray
    ): Boolean {
        if (maze.isEmpty()) return false
        val visited = Array(maze.size) { BooleanArray(maze.first().size) { false } }
        val dirs = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, -1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
        )
        val queue = ArrayDeque<IntArray>()
        queue.add(start)
        visited[start[0]][start[1]] = true
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
            if (row == destination[0] && col == destination[1]) {
                return true
            }
            for ((rowDiff, colDiff) in dirs) {
                var x = row + rowDiff
                var y = col + colDiff
                while (x in maze.indices && y in maze.first().indices && maze[x][y] == 0) {
                    x = row + rowDiff
                    y = col + colDiff
                }
                x -= rowDiff
                y -= colDiff
                if (!visited[x][y]) {
                    queue.add(intArrayOf(x, y))
                    visited[x][y] = true
                }
            }
        }
        return false
    }
}