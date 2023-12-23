package com.blankj.medium._2316

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun countPairs(n: Int, edges: Array<IntArray>): Long {
        if (edges.isEmpty()) return (n.toLong() * (n -1)) / 2L
        val find = IntArray(n) { it }
        val sizes = IntArray(n) { 1 }
        for (edge in edges) {
            val (first, second) = edge
            merge(find, first, second, sizes)
        }
        var res = 0L
        for (i in sizes.indices) {
            res += n - sizes[find(find, i)]
        }
        return res / 2
    }

    fun merge(find: IntArray, x: Int, y: Int, sizes: IntArray) {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx == fy) return
        if (sizes[fx] >= sizes[fy]) {
            find[fy] = fx
            sizes[fx] += sizes[fy]
        } else {
            find[fx] = fy
            sizes[fy] += sizes[fx]
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var root = x
        while (root != find[root]) {
            root = find[root]
        }
        find[x] = root
        return root
    }
}

fun main() {
    Solution().countPairs(
        3,
        MultiDimensionArray.createTestData("[[0,1],[0,2],[1,2]]")
    ).print()
    Solution().countPairs(
        7,
        MultiDimensionArray.createTestData("[[0,2],[0,5],[2,4],[1,6],[5,4]]")
    ).print()
    Solution().countPairs(
        12,
        MultiDimensionArray.createTestData("[[2,6],[11,3],[5,4],[9,6]]")
    ).print()
}