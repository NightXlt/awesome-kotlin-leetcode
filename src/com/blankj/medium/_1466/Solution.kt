package com.blankj.medium._1466


import java.util.*
import kotlin.math.abs
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Solution {
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        if (connections.isEmpty()) return 0
        val map = HashMap<Int, HashSet<Int>>()
        for (connection in connections) {
            val adjacencyList = map.getOrDefault(connection[0], HashSet())
            adjacencyList.add(connection[1])
            map[connection[0]] = adjacencyList
            val reverseAdjacencyList = map.getOrDefault(connection[1], HashSet())
            reverseAdjacencyList.add(-connection[0])
            map[connection[1]] = reverseAdjacencyList
        }
        return dfs(map, n)
    }

    private fun dfs(map: HashMap<Int, HashSet<Int>>, n: Int): Int {
        val flag = BooleanArray(n)
        val queue = LinkedList<Int>()
        queue.add(0)
        flag[0] = true
        var res = 0
        while (queue.isNotEmpty()) {
            val adjacencyList = map[queue.poll()] ?: return 0
            for (i in adjacencyList) {
                val absI = abs(i)
                if (flag[absI]) continue
                if (i > 0) res++
                queue.add(absI)
                flag[absI] = true
            }
        }
        return res
    }
}