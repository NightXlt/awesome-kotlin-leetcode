package com.blankj.medium._348

import com.blankj.ext.print

class TicTacToe(n: Int) {

    // 使用rows, cols, diagonals 记录每个玩家在行，列，对角线落子综合，若其中一方先到达n，先获胜
    private var n: Int = n
    private val rows = Array(3) { IntArray(n) }
    private val cols = Array(3) { IntArray(n) }
    private val diagonal = Array(3) { IntArray(2) }

    /** Initialize your data structure here. */


    /** Player {player} makes a move at ({row}, {col}).
    @param row The row of the board.
    @param col The column of the board.
    @param player The player, can be either 1 or 2.
    @return The current winning condition, can be either:
    0: No one wins.
    1: Player 1 wins.
    2: Player 2 wins. */
    fun move(row: Int, col: Int, player: Int): Int {
        if (++rows[player][row] == n) return player
        if (++cols[player][col] == n) return player
        if (row == col && ++diagonal[player][0] == n) {
            return player
        }
        if (row + col == n - 1 && ++diagonal[player][1] == n) {
            return player
        }
        return 0
    }

}

fun main() {
    var toe = TicTacToe(3)
    toe.move(0, 0, 1).print()
    toe.move(0, 2, 2).print()
    toe.move(2, 2, 1 ).print()
    toe.move(1, 1, 2).print()
    toe.move(2, 0, 1).print()
    toe.move(1, 0, 2).print()
    toe.move(2, 1, 1).print()

}