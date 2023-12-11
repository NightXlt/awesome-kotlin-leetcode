package com.blankj.hard._815

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {

    private var find = IntArray(N.toInt())
    private lateinit var routes: Array<IntArray>
    private var source: Int = 0
    private var target: Int = 0
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if (routes.isEmpty()) return 0
        if (source == target) return 0
        this.routes = routes
        this.source = source
        this.target = target
        find.indices.forEach { find[it] = it }
        for (route in routes) {
            for (index in route) {
                merge(find, index, route[0])
            }
        }
        if (find(find, source) != find(find, target)) {
            return -1
        }
        return doubleBfs()
    }

    // graph of station and lines number mapping
    private val graph = mutableMapOf<Int, MutableSet<Int>>()
    private fun doubleBfs(): Int {
        val m1 = mutableMapOf<Int, Int>()
        val m2 = mutableMapOf<Int, Int>()
        val d1 = ArrayDeque<Int>()
        val d2 = ArrayDeque<Int>()
        for ((i, route) in routes.withIndex()) {
            for (station in route) {
                // 将从起点可以进入的路线加入正向队列
                if (station == source) {
                    m1[i] = 1
                    d1.add(i)
                }
                // 将从终点可以进入的路线加入反向队列
                if (station == target) {
                    m2[i] = 1
                    d2.add(i)
                }
                val set = graph.getOrPut(station) { mutableSetOf() }
                set.add(i)
            }
        }
        val s1 = graph.getValue(source)
        val s2 = graph.getValue(target)
        val total = s1.toMutableSet()
        total.retainAll(s2)
        // has intersection means in the same line
        if (total.isNotEmpty()) return 1
        while (d1.isNotEmpty() && d2.isNotEmpty()) {
            val res = if (d1.size < d2.size) {
                update(d1, m1, m2)
            } else {
                update(d2, m2, m1)
            }
            if (res != -1) return res
        }
        return -1
    }

    private fun update(d: ArrayDeque<Int>, cur: MutableMap<Int, Int>, other: MutableMap<Int, Int>): Int {
        val size = d.size
        repeat(size) {
            // 取出当前所在的路线，与进入该路线所花费的距离
            val curLine = d.removeFirst()
            val step = cur.getValue(curLine)
            // 遍历该路线所包含的车站
            for (station in routes[curLine]) {
                // 遍历将由该线路的车站发起的路线
                val lines = graph[station] ?: continue
                for (line in lines) {
                    if (cur.containsKey(line)) continue
                    if (other.containsKey(line)) return step + other.getValue(line)
                    cur[line] = step + 1
                    d.add(line)
                }
            }
        }
        return -1
    }

    private fun merge(find: IntArray, x: Int, y: Int) {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
            find[x] = fy
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var temp = x
        while (find[temp] != temp) {
            temp = find[temp]
        }
        return temp
    }

    companion object {
        private const val N = 1e6 + 10
    }
}

fun main() {
    Solution().numBusesToDestination(
        MultiDimensionArray.createTestData("[[1,2,7],[3,6,7]]"),
        1, 6
    ).print()
}