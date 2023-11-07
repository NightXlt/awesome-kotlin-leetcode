package com.blankj.hard._35

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import java.util.*

class Solution {
    fun electricCarPlan(paths: Array<IntArray>, cnt: Int, start: Int, end: Int, charge: IntArray): Int {
        val n = charge.size
        val graph = mutableMapOf<Int, MutableList<IntArray>>()
        repeat(n) {
            graph[it] = mutableListOf()
        }
        for (edge in paths) {
            val (x, y, distance) = edge
            graph[x]?.add(intArrayOf(y, distance))
            graph[y]?.add(intArrayOf(x, distance))
        }
        val res = Array(n) { IntArray(cnt + 1) { Int.MAX_VALUE / 2 } }
        res[start][0] = 0
        val queue = PriorityQueue<IntArray>(Comparator.comparingInt {
            it[1]
        })
        queue.add(intArrayOf(start, 0, 0))
        while (queue.isNotEmpty()) {
            // cur: 当前节点  time: 已用时间  power：剩余电量
            val (cur, time, power) = queue.poll()
            if (time > res[cur][power]) continue
            if (cur == end) return time
            if (power < cnt) {
                val t = time + charge[cur]
                if (t < res[cur][power + 1]) {
                    res[cur][power + 1] = t
                    queue.add(intArrayOf(cur, t, power + 1))
                }
            }
            for (next in graph.getValue(cur)) {
                val (nextNode, distance) = next
                val t = time + distance
                val p = power - distance
                if (p >= 0 && t < res[nextNode][p]) {
                    res[nextNode][p] = t
                    queue.add(intArrayOf(nextNode, t, p))
                }
            }
        }
        return -1
    }
}

fun main() {
    Solution().electricCarPlan(
        MultiDimensionArray.createTestData("[[1,3,3],[3,2,1],[2,1,3],[0,1,4],[3,0,5]]"),
        6, 1,0, intArrayOf(2,10,4,1)
    ).print()
    Solution().electricCarPlan(
        MultiDimensionArray.createTestData("[[0,4,2],[4,3,5],[3,0,5],[0,1,5],[3,2,4],[1,2,8]]"),
        8, 0,2, intArrayOf(4,1,1,3,2)
    ).print()
}