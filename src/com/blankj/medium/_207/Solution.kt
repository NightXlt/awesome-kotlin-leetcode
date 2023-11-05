package com.blankj.medium._207

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        if (numCourses <= 0) return false
        val inDegree = IntArray(numCourses)
        for (prerequisite in prerequisites) {
            inDegree[prerequisite[0]]++
        }
        val queue = ArrayDeque<Int>()
        queue.addAll(inDegree.indices.filter { inDegree[it] == 0 })
        var res = 0
        while (queue.isNotEmpty()) {
            res++
            val curElement = queue.removeFirst()
            for (prerequisite in prerequisites) {
                if (prerequisite[1] == curElement) {
                    inDegree[prerequisite[0]]--
                    if (inDegree[prerequisite[0]] == 0) queue.add(prerequisite[0])
                }
            }
        }
        return res == numCourses
    }
}

fun main() {
    Solution().canFinish(
        2, arrayOf(
            intArrayOf(1, 0)
        )
    ).print()
    Solution().canFinish(
        4, arrayOf(
            intArrayOf(1, 0),
            intArrayOf(2, 0),
            intArrayOf(3, 1),
            intArrayOf(3, 2)
        )
    ).print()
    Solution().canFinish(
        1, arrayOf(
        )
    ).print()
    Solution().canFinish(
        2,
            MultiDimensionArray.createTestData("[[1,0],[0,1]]")
        ).print()
}
