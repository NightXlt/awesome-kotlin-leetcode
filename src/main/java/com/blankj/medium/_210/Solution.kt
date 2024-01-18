package com.blankj.medium._210

import com.blankj.ext.print

class Solution {
    /**
     *   1. add 0 degree edge into queue
     *   2. traverse queue, and remove node
     *   3. update node value(minus 1), and add node of value == 0 status
     *   4. until queue is empty
     */
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        if (numCourses <= 0) return intArrayOf()
        val inDegree = IntArray(numCourses)
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (prerequisite in prerequisites) {
            inDegree[prerequisite[0]]++
            graph.getOrPut(prerequisite[1]) { mutableListOf() }.add(prerequisite[0])
        }
        val queue = ArrayDeque<Int>()
        queue.addAll(inDegree.indices.filter { inDegree[it] == 0 })
        val res = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val course = queue.removeFirst()
            res.add(course)
            if (course !in graph) continue
            for (next in graph.getValue(course)) {
                inDegree[next]--
                if (inDegree[next] == 0) queue.add(next)
            }
        }
        if (res.size != numCourses) return intArrayOf()
        return res.toIntArray()
    }
}

fun main() {
    Solution().findOrder(
        2, arrayOf(
            intArrayOf(1, 0)
        )
    ).print()
    Solution().findOrder(
        4, arrayOf(
            intArrayOf(1, 0),
            intArrayOf(2, 0),
            intArrayOf(3, 1),
            intArrayOf(3, 2)
        )
    ).print()
    Solution().findOrder(
        1, arrayOf(
        )
    ).print()
}
