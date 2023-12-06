package com.blankj.medium._323

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val find = IntArray(n) { it }
        for (edge in edges) {
            merge(find, edge[0], edge[1])
        }
        var count = 0
        for (i in find.indices) {
            if (find[i] == i) {
                count++
            }
        }
        return count
    }

    private fun merge(find: IntArray, x: Int, y: Int) {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var root = x
        while (root != find[root]) {
            root = find[root]
        }
        return root
    }
}

fun main() {
    Solution().countComponents(5, MultiDimensionArray.createTestData("[[0,1],[1,2],[3,4]]")).print()
    Solution().countComponents(5, MultiDimensionArray.createTestData("[[0,1],[1,2],[2,3],[3,4]]")).print()
}