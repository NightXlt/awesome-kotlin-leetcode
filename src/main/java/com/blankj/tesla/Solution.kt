package com.blankj.tesla

import com.blankj.ext.print


class Guard(
    val x: Int,
    val y: Int,
    val direction: Direction
) {
    enum class Direction {
        UP,
        LEFT,
        RIGHT,
        DOWN;

        fun reverse(): Direction {
            val values = values()
            return values[values.size - this.ordinal]
        }
    }

    fun isFaceToFaceGuard(x: Int, y: Int, guards: Set<Guard>) {
        for (guard in guards) {
            if (guard != this) {
                if (guard.direction == this.direction.reverse()) {
                    if (guard.direction == Direction.DOWN || guard.direction == Direction.UP) {
                        y
                    }
                }
            }
        }
    }

    fun canSee(x: Int, y: Int): Boolean {
        return when (direction) {
            Direction.UP -> y == this.y && x <= this.x
            Direction.DOWN -> y == this.y && x >= this.x
            Direction.LEFT -> x == this.x && y <= this.y
            Direction.RIGHT -> x == this.x && y >= this.y
        }
    }
}

fun dfs(B: Array<String>): Boolean {
    if (B.isEmpty() || B[0].isEmpty()) return false
    val map = mapOf(
        '<' to Guard.Direction.LEFT,
        '>' to Guard.Direction.RIGHT,
        '^' to Guard.Direction.UP,
        'v' to Guard.Direction.DOWN,
    )
    val visited = mutableMapOf<Int, Boolean>()
    val guards = B.mapIndexedNotNull { i, row ->
        row.indices.filter { row[it] in map }
            .map { j ->
                Guard(i, j, map.getValue(row[j]))
            }
    }.flatten().toSet()
    return dfs(B, guards, 0, 0, visited)
}


fun dfs(board: Array<String>, guards: Set<Guard>, x: Int, y: Int, visited: MutableMap<Int, Boolean>): Boolean {
    if (x == board.size - 1 && y == board[0].length - 1 && !guards.any { it.canSee(x, y) }) {
        return true
    }
    if (x !in board.indices || y !in board[0].indices) {
        return false
    }
    if (x * board[0].length + y in visited) {
        return false
    }
    visited[x * board[0].length + y] = true
    if (board[x][y] != '.') {
        return false
    }
    for (guard in guards) {
        if (guard.canSee(x, y)) {
            return false
        }
    }

    return dfs(board, guards, x + 1, y, visited) ||
            dfs(board, guards, x - 1, y, visited) ||
            dfs(board, guards, x, y + 1, visited) ||
            dfs(board, guards, x, y - 1, visited)
}

fun main() {
    dfs(
        arrayOf(
            "X.....>",
            "..v..X.",
            "..v..X.",
            ".>..X..",
            "A......",
        )
    ).print()
    dfs(
        arrayOf(
            "...Xv",
            "AX..^",
            ".XX..",
        )
    ).print()
}