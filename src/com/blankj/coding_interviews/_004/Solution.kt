package com.blankj.coding_interviews._004

import com.blankj.structure.ListNode
import com.blankj.structure.TreeNode

class Solution {
    fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false
        var row = 0
        var col = matrix[0].lastIndex
        while (row < matrix.size && col >= 0) {
            when {
                matrix[row][col] == target -> return true
                matrix[row][col] > target -> col--
                else -> row++
            }
        }
        return false
    }
}

fun main() {
    val matrix = arrayOf(
            intArrayOf(1, 4, 7, 11, 15),
            intArrayOf(2, 5, 8, 12, 19),
            intArrayOf(3, 6, 9, 16, 22),
            intArrayOf(10, 13, 14, 17, 24),
            intArrayOf(18, 21, 23, 26, 30)
    )
    // Test cases
    Solution().findNumberIn2DArray(matrix, 1).print() // min values
    Solution().findNumberIn2DArray(matrix, 30).print() // max value
    Solution().findNumberIn2DArray(matrix, 20).print() // don't exist
    Solution().findNumberIn2DArray(emptyArray(), -1).print() // empty array
}

fun Any.print() {
    when (this) {
        is Collection<*> -> {
            println(this.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
        is IntArray -> {
            println(this.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
        is ListNode -> {
            print("$`val` ")
            next?.print() ?: println()
        }
        is TreeNode -> {
            TreeNode.print(this)
        }
        is DoubleArray -> {
            println(this.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
        else -> println(this.toString())

    }
}