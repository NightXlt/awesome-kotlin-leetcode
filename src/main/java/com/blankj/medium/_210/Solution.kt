package com.blankj.medium._210

import com.blankj.ext.print
import java.util.*

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
        for (prerequisite in prerequisites) {
            inDegree[prerequisite[0]]++
        }
        val queue: Queue<Int> = ArrayDeque()
        for ((index, i) in inDegree.withIndex()) {
            if (i == 0) queue.offer(index)
        }
        val res = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val curElement = queue.poll()
            res.add(curElement)
            for (prerequisite in prerequisites) {
                if (prerequisite[1] == curElement) {
                    inDegree[prerequisite[0]]--
                    if (inDegree[prerequisite[0]] == 0) queue.add(prerequisite[0])
                }
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
