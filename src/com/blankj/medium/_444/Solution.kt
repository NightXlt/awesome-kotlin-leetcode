package com.blankj.medium._444

import com.blankj.ext.print

class Solution {
    fun sequenceReconstruction(nums: IntArray, sequences: List<List<Int>>): Boolean {
        if (nums.isEmpty() && sequences.isEmpty()) return true
        if (nums.isEmpty() || sequences.isEmpty()) return false
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        val inDegrees = mutableMapOf<Int, Int>()
        for (seq in sequences) {
            for (num in seq) {
                if (num < 1 || num > nums.size) {
                    return false
                }
                graph.putIfAbsent(num, mutableSetOf())
                inDegrees.putIfAbsent(num, 0)
            }
            constructDirectedGraph(seq, graph, inDegrees)
        }
        val queue = ArrayDeque<Int>()
        queue.addAll(inDegrees.keys.filter { inDegrees[it] == 0 })
        val res = mutableListOf<Int>()
        while (queue.size == 1) {
            val num = queue.removeFirst()
            res.add(num)
            for (next in graph.getValue(num)) {
                inDegrees[next] = inDegrees[next]!! - 1
                if (inDegrees[next] == 0) {
                    queue.add(next)
                }
            }
        }
        return res.toIntArray().contentEquals(nums)
    }

    private fun constructDirectedGraph(
        seq: List<Int>,
        graph: MutableMap<Int, MutableSet<Int>>,
        inDegrees: MutableMap<Int, Int>
    ) {
        for (i in 1 until seq.size) {
            val num1 = seq[i - 1]
            val num2 = seq[i]
            if (!graph.getValue(num1).contains(num2)) {
                graph.getValue(num1).add(num2)
                inDegrees[num2] = inDegrees[num2]!! + 1
            }
        }
    }
}

fun main() {
    Solution().sequenceReconstruction(
        intArrayOf(1, 2, 3),
        listOf(
            listOf(1, 2),
            listOf(1,3),
        )
    ).print()
    Solution().sequenceReconstruction(
        intArrayOf(1, 2, 3),
        listOf(
            listOf(1, 2),
            listOf(1,3),
            listOf(2,3),
        )
    ).print()
}