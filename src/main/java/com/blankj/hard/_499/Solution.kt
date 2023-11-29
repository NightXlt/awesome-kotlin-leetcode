package com.blankj.hard._499

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    val dirs = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
        intArrayOf(-1, 0),
        intArrayOf(0, 0)
    )
    var dirToPath = charArrayOf(
        'd', 'l', 'r', 'u'
    )

    fun findShortestWay(maze: Array<IntArray>, ball: IntArray, hole: IntArray): String {
        if (maze.isEmpty()) return ""
        val visited = Array(maze.size) { Array(maze.first().size) { BooleanArray(4) } }
        val queue = ArrayDeque<Node>()
        queue.add(Node(ball[0], ball[1], 4, ""))
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node.x == hole[0] && node.y == hole[1]) {
                return node.path
            }
            bfs(node, maze, visited, queue)
        }
        return "impossible"
    }

    private fun bfs(
        node: Node,
        maze: Array<IntArray>,
        visited: Array<Array<BooleanArray>>,
        queue: ArrayDeque<Node>
    ) {
        val dir = node.dir
        var x = node.x + dirs[dir][0]
        var y = node.y + dirs[dir][1]
        if (dir == 4 || x !in maze.indices || y !in maze.first().indices || maze[x][y] == 1) {
            for (i in 0..<4) {
                if (i == dir) continue
                x = node.x + dirs[i][0]
                y = node.y + dirs[i][1]
                if (x in maze.indices && y in maze.first().indices && maze[x][y] ==0 && !visited[x][y][i]) {
                    visited[x][y][i] = true
                    queue.add(Node(x, y, i, node.path + dirToPath[i]))
                }
            }
        } else if (!visited[x][y][dir]) {
            visited[x][y][dir] = true
            // 如果需要追溯全链路， 下面补上 , path = node.path + dirToPath[node.dir] 就好了
            queue.add(node.copy(x = x, y = y))
        }
    }

    data class Node(
        val x: Int,
        val y: Int,
        val dir: Int,
        val path: String
    )
}

fun main() {
    Solution().findShortestWay(
        MultiDimensionArray.createTestData(
            "[[0,1,0,1,0,0,0,0,0,0,1],[0,1,0,1,1,1,0,1,1,0,0],[1,0,0,0,0,0,0,0,0,0,1],[0,0,0,1,1,1,0,1,0,0,1],[1,1,0,0,0,1,0,0,0,1,1],[0,1,0,0,0,0,0,1,0,1,0],[0,0,0,0,1,0,0,1,1,1,0]]",
        ),
        intArrayOf(0, 4),
        intArrayOf(1, 6)
    ).print()
}