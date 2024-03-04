package com.blankj.duolingo

import com.blankj.ext.print

/**
 * Time Complexity: O(4^n * n)
 * Space Complexity: O(4^n)
 *
 * Here, n represents the size of the board.
 *
 */

class MoveInBoard {

    /**
     * if the count of R, B doesn't equal, no need to dfs.
     */
    private fun isValidMove(start: List<String>, end: List<String>): Boolean {
        // Check if the number of red and black tiles is the same in both states
        val redCountStart = start.count { it == "R" }
        val blackCountStart = start.count { it == "B" }
        val redCountEnd = end.count { it == "R" }
        val blackCountEnd = end.count { it == "B" }
        return redCountStart == redCountEnd && blackCountStart == blackCountEnd
    }

    fun dfs(currentMove: Move, visited: MutableSet<String>, stack: ArrayDeque<List<String>>): Boolean {
        if (currentMove.board == end) {
            return true
        }

        visited.add(currentMove.sequence)

        val possibleMoves = currentMove.generateMoves()
        for (move in possibleMoves) {
            if (move.sequence !in visited) {
                stack.add(move.board)
                val result = dfs(move, visited, stack)
                if (result) {
                    return true
                }
                stack.removeLast()
            }
        }
        return false
    }

    var end: List<String> = listOf()
    fun findPath(start: List<String>, end: List<String>): List<List<String>>? {
        if (!isValidMove(start, end)) {
            return null
        }
        this.end = end
        val startStr = start.joinToString("")
        val startMove = Move(start, startStr)
        val visited = mutableSetOf<String>()
        val stack = ArrayDeque<List<String>>()
        stack.add(start)
        dfs(startMove, visited, stack)
        return stack.toList()
    }
}

data class Move(val board: List<String>, val sequence: String) {
    fun generateMoves(): List<Move> {
        val moves = mutableListOf<Move>()

        for (i in 0 until board.size - 1) {
            if (board[i] == "R" && board[i + 1] == "_") {
                val newBoard = board.toMutableList()
                newBoard[i] = "_"
                newBoard[i + 1] = "R"
                moves.add(Move(newBoard, newBoard.joinToString(separator = "")))
            }

            if (board[i] == "R" && board[i + 1] == "B" && i < board.size - 2 && board[i + 2] == "_") {
                val newBoard = board.toMutableList()
                newBoard[i] = "_"
                newBoard[i + 2] = "R"
                moves.add(Move(newBoard, newBoard.joinToString(separator = "")))
            }

            if (board[i] == "_" && board[i + 1] == "B") {
                val newBoard = board.toMutableList()
                newBoard[i] = "B"
                newBoard[i + 1] = "_"
                moves.add(Move(newBoard, newBoard.joinToString(separator = "")))
            }

            if (board[i] == "_" && board[i + 1] == "R" && i < board.size - 2 && board[i + 2] == "B") {
                val newBoard = board.toMutableList()
                newBoard[i] = "B"
                newBoard[i + 2] = "_"
                moves.add(Move(newBoard, newBoard.joinToString(separator = "")))
            }
        }

        return moves
    }

}

fun main() {
    val start = System.currentTimeMillis()
    val start1 = listOf("R", "_", "B", "B")
    val end1 = listOf("B", "_", "B", "R")
    val path1 = MoveInBoard().findPath(start1, end1)
    println("Example #1:")
    path1?.print()

    val start2 = listOf("R", "R", "_")
    val end2 = listOf("_", "R", "R")
    val path2 = MoveInBoard().findPath(start2, end2)
    println("\nExample #2:")
    path2?.print()
    println(System.currentTimeMillis() - start)

    val elements = listOf(Int.MAX_VALUE, Int.MAX_VALUE, 3)
    val elements1 = listOf(Int.MAX_VALUE, 3, Int.MAX_VALUE)
    println(elements.hashCode())
    println(elements1.hashCode())

}
