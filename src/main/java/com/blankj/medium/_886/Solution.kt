package com.blankj.medium._886

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    private fun setColor(
        graph: Map<Int, List<Int>>,
        colors: IntArray,
        i: Int,
        color: Int
    ): Boolean {
        val queue = ArrayDeque<Int>()
        colors[i] = color
        queue.add(i)
        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            for (next in graph[cur]!!) {
                if (colors[next] >= 0) {
                    if (colors[next] == colors[cur]) {
                        return false
                    }
                } else {
                    queue.add(next)
                    colors[next] = 1 - colors[cur]
                }
            }
        }
        return true
    }

    fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
        val colors = IntArray(n + 1) { -1 }
        val graph = mutableMapOf<Int, MutableList<Int>>()
        repeat(n + 1) { graph[it] = mutableListOf() }
        for (edge in dislikes) {
            graph[edge[0]]?.add(edge[1])
            graph[edge[1]]?.add(edge[0])
        }
        for (i in 1..n) {
            if (colors[i] == -1 && !setColor(graph, colors, i, 0)) {
                return false
            }
        }
        return true
    }
}

fun main() {
    Solution().possibleBipartition(
        4,
        MultiDimensionArray.createTestData("[[1,2],[1,3],[2,4]]")
    ).print()
    Solution().possibleBipartition(
        3,
        MultiDimensionArray.createTestData("[[1,2],[1,3],[2,3]]")
    ).print()
    Solution().possibleBipartition(
        5,
        MultiDimensionArray.createTestData("[[1,2],[2,3],[3,4],[4,5],[1,5]]")
    ).print()
}