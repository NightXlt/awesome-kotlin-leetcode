package com.blankj.medium._1462

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun checkIfPrerequisite(numCourses: Int, prerequisites: Array<IntArray>, queries: Array<IntArray>): List<Boolean> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        repeat(numCourses) { graph[it] = mutableListOf() }
        val inDegree = IntArray(numCourses)
        // isPre[i][j] 记录了 i 是否是 j 的先修课
        val isPre = Array(numCourses) { BooleanArray(numCourses) }
        for (prerequisite in prerequisites) {
            ++inDegree[prerequisite[1]]
            graph[prerequisite[0]]!!.add(prerequisite[1])
        }
        val queue = ArrayDeque<Int>()
        queue.addAll(inDegree.indices.filter { inDegree[it] == 0 })
        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            for (next in graph[cur]!!) {
                isPre[cur][next] = true
                // 遍历更新先修课的先修课到当前课程的状态
                for (i in 0 until numCourses) {
                    isPre[i][next] = isPre[i][next] or isPre[i][cur]
                }
                --inDegree[next]
                if (inDegree[next] == 0) {
                    queue.add(next)
                }
            }
        }
        val res = mutableListOf<Boolean>()
        for (query in queries) {
            res.add(isPre[query[0]][query[1]])
        }
        return res
    }
}

fun main() {
    Solution().checkIfPrerequisite(
        2,
        MultiDimensionArray.createTestData("[[1,0]]"),
        MultiDimensionArray.createTestData("[[0,1],[1,0]]"),
    ).print()

    Solution().checkIfPrerequisite(
        2,
        emptyArray(),
        MultiDimensionArray.createTestData("[[1,0],[0,1]]"),
    ).print()

    Solution().checkIfPrerequisite(
        3,
        MultiDimensionArray.createTestData("[[1,2],[1,0],[2,0]]"),
        MultiDimensionArray.createTestData("[[1,0],[1,2]]"),
    ).print()
}