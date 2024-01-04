package com.blankj.medium._36


class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val row = IntArray(9)
        val col = IntArray(9)
        val subBoxes = IntArray(9)
        for (i in 0..<9) {
            for (j in 0..<9) {
                val c = board[i][j]
                if (c == '.') continue
                val num = c - '0'
                val id = i / 3 * 3 + j / 3
                if (((row[i] shr num) or (col[j] shr num) or (subBoxes[id] shr num)) and 1 == 1) {
                    return false
                }
                row[i] = row[i] or (1 shl num)
                col[j] = col[j] or (1 shl num)
                subBoxes[id] = subBoxes[id] or (1 shl num)
            }
        }
        return true
    }
}