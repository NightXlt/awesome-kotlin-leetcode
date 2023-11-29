package com.blankj.medium._505

class Solution {
    fun shortestDistance(
        maze: Array<IntArray>,
        start: IntArray,
        destination: IntArray
    ): Int {
        if (maze.isEmpty()) return -1
        val dp = Array(maze.size) { IntArray(maze.first().size) { Int.MAX_VALUE } }
        val dirs = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, -1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0),
        )
        val queue = ArrayDeque<IntArray>()
        queue.add(start)
        dp[start[0]][start[1]] = 0
        while (queue.isNotEmpty()) {
            val (row, col) = queue.removeFirst()
            for ((rowDiff, colDiff) in dirs) {
                var x = row + rowDiff
                var y = col + colDiff
                var count = 0
                while (x in maze.indices && y in maze.first().indices && maze[x][y] == 0) {
                    x += rowDiff
                    y += colDiff
                    count++
                }
                x -= rowDiff
                y -= colDiff
                if (dp[row][col] + count < dp[x][y]) {
                    queue.add(intArrayOf(x, y))
                    dp[x][y] = dp[row][col] + count
                }
            }
        }
        return if (dp[destination[0]][destination[1]] == Int.MAX_VALUE) -1 else dp[destination[0]][destination[1]]
    }
}