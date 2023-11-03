package com.blankj.medium._785

import com.blankj.ext.print

class Solution {
    private fun setColor(graph: Array<IntArray>, colors: IntArray, i: Int, color: Int): Boolean {
        val queue = ArrayDeque<Int>()
        queue.add(i)
        colors[i] = color
        while (queue.isNotEmpty()) {
            val v = queue.removeFirst()
            for (neighbor in graph[v]) {
                // 找到我的邻居， 如果他们已经着色并和我的颜色相同则不是二分图
                // 二分图可以分为两种类型，任一条边两个节点可以拆分为两个集合，迁移下就是可以着色为不同颜色，如果同色了说明不是二分图
                if (colors[neighbor] >= 0) {
                    if (colors[neighbor] == colors[v]) {
                        return false
                    }
                } else {
                    queue.add(neighbor)
                    colors[neighbor] = 2 - colors[v]
                }
            }
        }
        return true
    }

    //    二分图着色有点东西
    fun isBipartite(graph: Array<IntArray>): Boolean {
        val colors = IntArray(graph.size) { -1 }
        for (i in graph.indices) {
            if (colors[i] == -1 && !setColor(graph, colors, i, 0)) {
                return false
            }
        }
        return true
    }

    private fun dfs(graph: Array<IntArray>, colors: IntArray, i: Int, color: Int): Boolean {
        if (colors[i] >= 0) {
            return colors[i] == color
        }
        colors[i] = color
        for (neighbor in graph[i]) {
            if (!dfs(graph, colors, neighbor, 1 - color)) {
                return false
            }
        }
        return true
    }
}

fun main() {
    Solution().isBipartite(
        arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(0, 2),
            intArrayOf(0, 1, 3),
            intArrayOf(0, 2),
        )
    ).print()
    Solution().isBipartite(
        arrayOf(
            intArrayOf(1, 3),
            intArrayOf(0, 2),
            intArrayOf(1, 3),
            intArrayOf(0, 2),
        )
    ).print()
}