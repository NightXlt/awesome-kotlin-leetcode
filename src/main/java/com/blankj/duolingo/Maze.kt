package com.blankj.duolingo

import com.blankj.ext.print

/**
 * Time Complexity: O(n^2 + k)
 * Space Complexity: O(n^2)
 * Here, n represents the size of the maze, and k represents the number of moves/tracks.
 */
class Maze {

    data class Node(
        val x: Int,
        val y: Int,
        val path: MutableList<String>
    )

    private var maze = arrayOf<IntArray>()
    fun goToMaze(n: Int, tracks: List<String>): List<String> {
        maze = Array(n) { IntArray(n) }
        buildUpMaze(tracks)
        val visited = Array(n) { BooleanArray(n) }

        val queue = ArrayDeque<Node>()
        val startNode = Node(0, 0, mutableListOf())
        queue.add(startNode)
        visited[0][0] = true
        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            if (cur.x == endX && cur.y == endY) {
                return cur.path
            }
            bfs(cur, visited, queue)
        }
        return tracks
    }

    private fun bfs(startNode: Node, visited: Array<BooleanArray>, queue: ArrayDeque<Node>) {
        for ((direction, diff) in dirMapping) {
            val (rowDiff, colDiff) = diff
            val x = startNode.x + rowDiff
            val y = startNode.y + colDiff
            if (x in maze.indices && y in maze.first().indices && maze[x][y] != 0 && !visited[x][y]) {
                visited[x][y] = true
                val newPath = startNode.path.toMutableList().apply { add(direction) }
                queue.add(Node(x = x, y = y, path = newPath))
            }
        }
    }

    private val dirMapping = mapOf(
        "south" to (1 to 0),
        "north" to (-1 to 0),
        "east" to (0 to 1),
        "west" to (0 to -1),
    )

    private var endX = 0
    private var endY = 0

    private fun buildUpMaze(tracks: List<String>) {
        var x = 0
        var y = 0
        maze[0][0] = 1
        tracks.forEach {
            if (it !in dirMapping) error("Illegal input track: $it")
            val (rowDiff, colDiff) = dirMapping.getValue(it)
            x += rowDiff
            y += colDiff
            maze[x][y] = 1
        }
        endX = x
        endY = y
    }
}

fun main() {
    Maze().goToMaze(
        10, listOf(
            "south",
            "east",
            "east",
            "south",
            "south",
            "west",
            "west",
            "east",
            "east",
            "south",
        )
    ).print()
}