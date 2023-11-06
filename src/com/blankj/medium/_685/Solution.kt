package com.blankj.medium._685

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
        val n = edges.size
        val find = IntArray(n + 1) { it } // 并查集用来判断是否成环
        // parent 简单判断入度是否 > 2, 同时记录了如果成环点的始点
        val parent = IntArray(n + 1) { it }

        var conflict = -1
        var cycle = -1
        for (i in 0 until n) {
            val edge = edges[i]
            val node1 = edge[0]
            val node2 = edge[1]
            if (parent[node2] != node2) {
                conflict = i
            } else {
                parent[node2] = node1
                if (find(find, node1) == find(find, node2)) {
                    cycle = i
                } else {
                    merge(find, node1, node2)
                }
            }
        }
        return if (conflict < 0) { // 指向根节点
            intArrayOf(edges[cycle][0], edges[cycle][1])
        } else { // 入度 > 2 的情况
            val conflictEdge = edges[conflict]
            if (cycle >= 0) { // 如果同时也成环了
                intArrayOf(parent[conflictEdge[1]], conflictEdge[1])
            } else {
                intArrayOf(conflictEdge[0], conflictEdge[1])
            }
        }
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


fun main() {
    Solution().findRedundantDirectedConnection(
        MultiDimensionArray.createTestData("[[2,1],[3,1],[4,2],[1,4]]")
    ).print()
    Solution().findRedundantDirectedConnection(
        MultiDimensionArray.createTestData("[[1,2],[1,3],[2,3]]")
    ).print()
    Solution().findRedundantDirectedConnection(
        MultiDimensionArray.createTestData("[[1,2],[2,3],[3,4],[4,1],[1,5]]")
    ).print()

}
