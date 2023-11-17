package com.blankj.medium._1138

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {
        val inDegree = IntArray(n + 1)
        val graph = mutableMapOf<Int, MutableList<Int>>()
        repeat(n) { graph[it + 1] = mutableListOf() }
        relations.forEach {
            inDegree[it[1]]++
            graph[it[0]]?.add(it[1])
        }

        val queue = ArrayDeque<Int>()
        for (i in 1..n) {
            if (inDegree[i] == 0) {
                queue.add(i)
            }
        }
        var res = 0
        var count = 0
        while (queue.isNotEmpty()) {
            res += 1
            repeat(queue.size) {
                val cur = queue.removeFirst()
                count++
                for (next in graph[cur]!!) {
                    inDegree[next]--
                    if (inDegree[next] == 0) {
                        queue.add(next)
                    }
                }
            }
        }
        return if (count == n) res else -1
    }
}

fun main() {
    Solution().minimumSemesters(3, MultiDimensionArray.createTestData("[[1,3],[2,3]]")).print()
    Solution().minimumSemesters(3, MultiDimensionArray.createTestData("[[1,2],[2,3],[3,1]]")).print()
}