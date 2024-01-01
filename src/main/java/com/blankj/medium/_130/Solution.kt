package com.blankj.medium._130

class Solution {
    fun solve(board: Array<CharArray>) {
        if (board.isEmpty()) return
        for (i in board.indices) {
            dfs(board, i, 0)
            dfs(board, i, board.first().lastIndex)
        }
        for (i in board.first().indices) {
            dfs(board, 0, i)
            dfs(board, board.lastIndex, i)
        }
        for (i in board.indices) {
            for (j in board.first().indices) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'
                } else if (board[i][j] == 'S') {
                    board[i][j] = 'O'
                }
            }
        }
    }

    val dirs = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
    )

    private fun dfs(board: Array<CharArray>, x: Int, y: Int) {
        if (x !in board.indices || y !in board.first().indices || board[x][y] != 'O') {
            return
        }
        board[x][y] = 'S'
        for ((rowDiff, colDiff) in dirs) {
            val nextX = x + rowDiff
            val nextY = y + colDiff
            dfs(board, nextX, nextY)
        }
    }
}