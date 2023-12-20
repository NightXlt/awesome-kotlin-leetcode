package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

// 2101
class MaximumDetonation {

    var max = Int.MIN_VALUE
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        if (bombs.isEmpty()) return 0
        max = Int.MIN_VALUE
        val graph = buildGraph(bombs)
        for (index in bombs.indices) {
            val visited = mutableSetOf<Int>()
            dfs(graph, visited, index)
            val num: Int = visited.size
            if (num == bombs.size) return num
            max = maxOf(max, num)
        }
        return max
    }

    private fun dfs(
        graph: List<List<Int>>,
        visited: MutableSet<Int>,
        index: Int,
    ){
        if (index in visited) return
        visited.add(index)
        for (neighbor in graph[index]) {
            dfs(graph, visited, neighbor)
        }
    }

    private fun buildGraph(bombs: Array<IntArray>): List<List<Int>> {
        return bombs.mapIndexed { index1, bomb1 ->
            bombs.mapIndexedNotNull { index2, bomb2 ->
                if (index1 != index2 && canExplode(bomb1, bomb2)) {
                    index2
                } else {
                    null
                }
            }
        }
    }

    private fun canExplode(bomb1: IntArray, bomb2: IntArray): Boolean {
        val dx: Long = (bomb1[0] - bomb2[0]).toLong()
        val dy: Long = (bomb1[1] - bomb2[1]).toLong()
        return (dx * dx + dy * dy) <= (bomb1[2] * bomb1[2].toLong())
    }
}

fun main() {
    MaximumDetonation().maximumDetonation(MultiDimensionArray.createTestData("[[2,1,3],[6,1,4]]")).print()
    MaximumDetonation().maximumDetonation(MultiDimensionArray.createTestData("[[1,1,5],[10,10,5]]")).print()
    MaximumDetonation().maximumDetonation(MultiDimensionArray.createTestData("[[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]")).print()
    MaximumDetonation().maximumDetonation(MultiDimensionArray.createTestData("[[37207,2653,5261],[40784,59523,20635],[16390,1426,39102],[42236,12,96855],[72839,62027,61667],[60691,58191,48447],[42932,46579,41248],[35868,43119,6870],[41693,98905,17374],[43441,1266,41621]]")).print()
}