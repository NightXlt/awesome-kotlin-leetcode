package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class GameOfLife {
    fun gameOfLife(board: Array<IntArray>) {
        if (board.isEmpty()) return
        for (i in board.indices) {
            for (j in board.first().indices) {
                val num = getLiveCell(board, i, j)
                if ((board[i][j] == 1 && num in 2..3) || (board[i][j] == 0 && num == 3)) {
                    board[i][j] = board[i][j] or (1 shl 1)
                }
            }
        }
        for (i in board.indices) {
            for (j in board.first().indices) {
                board[i][j] = board[i][j] shr 1
            }
        }
    }

    val dir = intArrayOf(0, 1, -1)
    private fun getLiveCell(board: Array<IntArray>, x: Int, y: Int): Int {
        var num = 0
        val invalidPair = 0 to 0
        for (i in dir) {
            for (j in dir) {
                if ((i to j) == invalidPair) continue
                val row = x + i
                val col = y + j
                if (row in board.indices && col in board.first().indices && (board[row][col] and 1) == 1) {
                    num++
                }
            }
        }
        return num
    }
}

fun main() {
    val board = MultiDimensionArray.createTestData("[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]")
    GameOfLife().gameOfLife(
        board
    )
    board.print()
}