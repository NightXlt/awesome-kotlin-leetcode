package com.blankj.medium._529

class Solution {
    var m = 0
    var n = 0

    fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
        if (board.isEmpty() || click.size < 2) return board
        val row = click[0]
        val col = click[1]
        if (board[row][col] == 'M') {
            board[row][col] = 'X'
            return board
        }
        m = board.size
        n = board[row].size
        dfs(row, col, board)
        return board
    }

    private fun dfs(row: Int, col: Int, board: Array<CharArray>) {
        if (row !in 0 until m || col !in 0 until n || board[row][col] != 'E') return
        var count = 0
        for (dir in dirs) {
            val x = row + dir[0]
            val y = col + dir[1]
            if (x in 0 until m && y in 0 until n && board[x][y] == 'M') count++
        }
        if (count != 0) {
            board[row][col] = '0' + count
            return
        }
        board[row][col] = 'B'
        for (dir in dirs) {
            val x = row + dir[0]
            val y = col + dir[1]
            dfs(x, y, board)
        }
    }

    companion object {
        private val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(-1, 1), intArrayOf(1, -1))
    }
}

fun main() {
    println(Solution().updateBoard(arrayOf(charArrayOf('E','E','E','E','E'), charArrayOf('E','E','E','E','E'),charArrayOf('E','E','E','E','E'),charArrayOf('E','E','E','E','E')), intArrayOf(3, 0)))

}