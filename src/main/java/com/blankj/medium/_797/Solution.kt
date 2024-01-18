package com.blankj.medium._797

import com.blankj.ext.print


class Solution {
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        val path = ArrayDeque<Int>()
        dfs(graph, 0, path, res)
        return res
    }

    private fun dfs(graph: Array<IntArray>, start: Int, path: ArrayDeque<Int>, res: MutableList<List<Int>>) {
        path.add(start)
        if (start == graph.lastIndex) {
            res.add(path.toList())
            path.removeLast()
            return
        }
        for (next in graph[start]) {
            dfs(graph, next, path, res)
        }
        path.removeLast()
    }
}

fun main() {
    Solution().allPathsSourceTarget(
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3),
            intArrayOf(3),
            intArrayOf(0),
        )
    ).print()
}