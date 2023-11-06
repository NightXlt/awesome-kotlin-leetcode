package com.blankj.medium._684

import kotlin.math.max

class Solution {
    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        if (edges.isEmpty()) return IntArray(2)
        val maxVertex = edges.maxOf {
            if (it.size < 2) error("Illegal edge array length: ${it.size}, and expected length is 2")
            return@maxOf max(it[0], it[1])
        }
        val find = IntArray(maxVertex + 1) { it }
        for (edge in edges) {
            if (!merge(find, edge[0], edge[1])) {
                return edge
            }
        }
        return IntArray(2)
    }

    private fun merge(find: IntArray, x: Int, y: Int): Boolean {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
            return true
        }
        return false
    }

    private fun find(find: IntArray, x: Int): Int {
        var index = x
        while (find[index] != index) {
            index = find[index]
        }
        return index
    }
}

