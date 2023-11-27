package com.blankj.medium._51

import com.blankj.ext.print

class Solution {

    private var rows = 0
    private var mainDig = 0
    private var secondDig = 0
    private lateinit var queens: IntArray
    var n = 0
    private val res = mutableListOf<List<String>>()

    fun solveNQueens(n: Int): List<List<String>> {
        this.n = n
        queens = IntArray(n)
        backTrack(0)
        return res
    }

    private fun backTrack(row: Int) {
        for (col in 0..<n) {
            if (isUnderAttack(row, col)) {
                continue
            }
            queens[row] = col
            if (row == n - 1) {
                addRes()
            }
            updateQueenStatus(row, col)
            backTrack(row + 1)
            updateQueenStatus(row, col)
        }
    }

    private fun updateQueenStatus(row: Int, col: Int) {
        val mainDigConstants = row - col + n - 1
        val secondDigConstants = row + col
        rows = rows xor (1 shl col)
        mainDig = mainDig xor (1 shl mainDigConstants)
        secondDig = secondDig xor (1 shl secondDigConstants)
    }

    private fun addRes() {
        val tempRes = mutableListOf<String>()
        repeat(n) { row ->
            tempRes.add(
                (0..<n).map {col ->
                    if (col == queens[row]) 'Q' else '.'
                }.joinToString(separator = "")
            )
        }
        res.add(tempRes)
    }

    private fun isUnderAttack(row: Int, col: Int): Boolean {
        return (((rows shr col) or (mainDig shr (row - col + n - 1)) or (secondDig shr (row + col))) and 1) != 0
    }
}


fun main() {
    Solution().solveNQueens(8).print()
}