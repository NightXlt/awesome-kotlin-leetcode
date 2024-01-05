package com.blankj.zoom

import com.blankj.ext.print

class HasCycle {

    fun hasCycle(matrix: Array<Array<String?>>): Boolean {
        if (matrix.isEmpty()) return false
        for (i in matrix.indices) {
            for (j in matrix.first().indices) {
                if (matrix[i][j] == null) continue
                visited = mutableSetOf()
                if (dfs(matrix, i, j)) {
                    return true
                }
            }
        }
        return false
    }

    private var visited = mutableSetOf<Int>()
    private fun dfs(matrix: Array<Array<String?>>, i: Int, j: Int): Boolean {
        val hash = 10001 * i + j
        if (hash in visited) {
            return true
        }
        if (i !in matrix.indices || j !in matrix[0].indices || matrix[i][j] == null) {
            return false
        }
        visited.add(hash)
        val cur = matrix[i][j]!!
        val row = cur.substring(1).toIntOrNull() ?: error("Illegal number format: $cur")
        val col = cur[0] - 'A'
        return dfs(matrix, row - 1, col)
    }
}

fun main() {
    HasCycle().hasCycle(
        arrayOf(
            arrayOf("B2", null, null, "A1"),
            arrayOf(null, "C3", null, null),
            arrayOf("A3", null, "D4", null),
            arrayOf(null, null, null, "A1"),
        )
    ).print()

    /**
     * [
     * [null, null, null, “A1”],
     * [null, “C3”, null, null],
     * [null, null, “D4”, null],
     * [null, null, null, “A1”]
     * ]
     *
     */
    HasCycle().hasCycle(
        arrayOf(
            arrayOf(null, null, null, "A1"),
            arrayOf(null, "C3", null, null),
            arrayOf(null, null, "D4", null),
            arrayOf(null, null, null, "A1"),
        )
    ).print()
    /**
     * [
     * [null, null, null, null],
     * [null, null, null, null],
     * [“A3”, null, null, null],
     * [null, null, null, null]
     * ]
     *
     */
    HasCycle().hasCycle(
        arrayOf(
            arrayOf(null, null, null, null),
            arrayOf(null, null, null, null),
            arrayOf("A3", null, null, null),
            arrayOf(null, null, null, null),
        )
    ).print()
}