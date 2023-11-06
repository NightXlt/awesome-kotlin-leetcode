package com.blankj.hard._1928

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import java.util.*

class Solution {
    fun minCost(maxTime: Int, edges: Array<IntArray>, passingFees: IntArray): Int {
        val n = passingFees.size
        val graph = mutableMapOf<Int, MutableList<IntArray>>()
        repeat(n) {
            graph[it] = mutableListOf()
        }
        for (edge in edges) {
            val (x, y, time) = edge
            graph[x]?.add(intArrayOf(y, time))
            graph[y]?.add(intArrayOf(x, time))
        }
        val queue = PriorityQueue<IntArray>(Comparator.comparingInt {
            it[1]
        })
        queue.add(intArrayOf(0, passingFees[0], 0))
        val timeMap = mutableMapOf<Int, Int>()
        while (queue.isNotEmpty()) {
            val (time, cost, node) = queue.poll()
            if (time > maxTime) continue
            if (node == n - 1) return cost
            if (!timeMap.contains(node) || timeMap.getValue(node) > time) {
                timeMap[node] = time
                for (next in graph.getValue(node)) {
                    queue.add(intArrayOf(next[1] + time, passingFees[next[0]] + cost, next[0]))
                }
            }
        }
        return -1
    }
}

fun main() {
    Solution().minCost(29,
        MultiDimensionArray.createTestData("[[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]]"),
        intArrayOf(5,1,2,20,20,3)
    ).print()
}