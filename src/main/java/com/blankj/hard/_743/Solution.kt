package com.blankj.hard._743

import kotlin.math.max


class Solution {
    // bfs + res(temp array) stores result set
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val graph = mutableMapOf<Int, MutableList<IntArray>>()
        repeat(n) { graph[it + 1] = mutableListOf() }
        for (edge in times) {
            if (edge.size < 3) error("edge size(${edge.size}) is illegal, required size: 3")
            if (!graph.contains(edge[0])) {
                graph[edge[0]] = mutableListOf()
            }
            graph[edge[0]]?.add(intArrayOf(edge[1], edge[2]))
        }
        val r = IntArray(n + 1) { if (it == 0) 0 else Int.MAX_VALUE }
        r[k] = 0
        val queue = ArrayDeque<IntArray>()
        queue.add(intArrayOf(k, 0))
        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            if (cur[0] in graph) {
                for (next in graph[cur[0]]!!) {
                    val t = next[1] + cur[1]
                    if (t < r[next[0]]) {
                        r[next[0]] = t
                        queue.add(intArrayOf(next[0], t))
                    }
                }
            }
        }
        var minT = 1
        for (i in 1..n) {
            minT = max(minT, r[i])
        }
        return if (minT == Int.MAX_VALUE) -1 else minT
    }
}