package com.blankj.hard._37

class Solution {
    var rows = IntArray(9) { 0 }
    var cols = IntArray(9) { 0 }
    var boxes = IntArray(9) { 0 }

    lateinit var board: Array<CharArray>
    var finished = false

    fun solveSudoku(board: Array<CharArray>) {
        this.board = board
        // 首先记录数独中已有的数字
        for (i in 0..8)
            for (j in 0..8)
                if (board[i][j] != '.') placeNumber(i, j, board[i][j] - '0')
        // 回溯
        backTracking(0, 0)
        println(" ")
    }

    private fun backTracking(row: Int, col: Int) {
        if (board[row][col] != '.') {
            // number case
            placeNextNumber(row, col)
            return
        }
        // 「.」 case
        for (i in 1..9) {
            // if i isn't available for cur position, so continue
            if (notSupportPlace(row, col, i)) continue
            placeNumber(row, col, i)
            placeNextNumber(row, col)
            if (finished) return
            removeNumber(row, col, i)
        }
    }

    private fun placeNumber(row: Int, column: Int, num: Int) {
        changeFlag(row, column, num)
        board[row][column] = '0' + num
    }

    private fun placeNextNumber(row: Int, column: Int) {
        if (row == 8 && column == 8) {
            finished = true
            return
        }
        if (column == 8) {
            backTracking(row + 1, 0)
            return
        }
        backTracking(row, column + 1)
    }


    private fun removeNumber(row: Int, column: Int, num: Int) {
        changeFlag(row, column, num)
        board[row][column] = '.'
    }

    private fun changeFlag(row: Int, column: Int, num: Int) {
        val index = (row / 3) * 3 + column / 3
        rows[row] = rows[row] xor (1 shl num)
        cols[column] = cols[column] xor (1 shl num)
        boxes[index] = boxes[index] xor (1 shl num)
    }

    private fun notSupportPlace(row: Int, column: Int, num: Int): Boolean {
        val index = (row / 3) * 3 + column / 3
        return (rows[row] shr num or (cols[column] shr num) or (boxes[index] shr num) and 1) != 0
    }
}
