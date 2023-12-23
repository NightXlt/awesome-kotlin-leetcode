package com.blankj.medium._1101

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
        logs.sortBy { it.firstOrNull() ?: error("Illegal empty array!") }
        val find = IntArray(n) { it }
        var count = 1
        for (log in logs) {
            if (merge(find, log[1], log[2])) {
                count++
            }
            if (count == n) {
                return log[0]
            }
        }
        return -1
    }

    private fun merge(find: IntArray, x: Int, y: Int): Boolean {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx == fy) return false
        find[fx] = fy
        return true
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
    Solution().earliestAcq(
        MultiDimensionArray.createTestData("[[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]]"),
        6
    ).print()
    Solution().earliestAcq(
        MultiDimensionArray.createTestData("[[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]]"),
        4
    ).print()
}