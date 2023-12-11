package com.blankj.hard._815

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import java.util.*
import kotlin.collections.ArrayDeque

class Solution {

    private lateinit var routes: Array<IntArray>
    private var source: Int = 0
    private var target: Int = 0

    /**
     * 单向 bfs 且以 station 作为访问入口， 更易懂且高效
     */
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if (routes.isEmpty()) return 0
        if (source == target) return 0
        this.routes = routes
        this.source = source
        this.target = target
        return bfs()
    }

    private fun bfs(): Int {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        val queue = ArrayDeque<Int>()
        val visitedStation = mutableSetOf<Int>()
        val visitedRoutes = BooleanArray(routes.size)
        for (route in routes.indices) {
            for (station in routes[route]) {
                val set = graph.getOrPut(station) { mutableSetOf() }
                set.add(route)
            }
        }
        queue.add(source)
        visitedStation.add(source)
        var res = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val station = queue.removeFirst()
                if (station == target) return res
                // 输入的 source 可能是非法的
                val routesOfCurStation = graph[station] ?: return@repeat
                for (route in routesOfCurStation) {
                    if (visitedRoutes[route]) continue
                    visitedRoutes[route] = true
                    for (nextStation in routes[route]) {
                        if (nextStation in visitedStation) continue
                        visitedStation.add(nextStation)
                        queue.add(nextStation)
                    }
                }
            }
            res++
        }
        return -1
    }
}

fun main() {
    Solution().numBusesToDestination(
        MultiDimensionArray.createTestData("[[1,2,7],[3,6,7]]"),
        1, 6
    ).print()
}