package com.blankj.medium._909

class Solution {
    fun snakesAndLadders(board: Array<IntArray>): Int {
        if (board.isEmpty()) error("Empty input")
        val queue = ArrayDeque<IntArray>()
        queue.add(intArrayOf(1, 0))
        val n = board.size
        val visited = BooleanArray(n * n + 1)
        visited[1] = true
        while (queue.isNotEmpty()) {
            val (id, count) = queue.removeFirst()
            for (i in 1..6) {
                var next = id + i
                if (next > n * n) {
                    break
                }
                val (row, col) = idToRC(next, n)
                if (board[row][col] > 0) {
                    next = board[row][col]
                }
                if (next == n * n) {
                    return count + 1
                }
                if (!visited[next]) {
                    visited[next] = true
                    queue.add(intArrayOf(next, count + 1))
                }
            }
        }
        return -1
    }

    private fun idToRC(id: Int, n: Int): IntArray {
        val row = (id - 1) / n
        var col = (id - 1) % n
        if (row % 2 == 1) {
            col = n - 1 - col
        }
        return intArrayOf(n - 1 - row, col)
    }
}