package com.blankj.hard._685

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
            val (node1, node2) = edges[i]
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
        // 不存在入度 > 2 的情况，必定是成环指向了根节点
        return if (conflict < 0) { // 指向根节点
            intArrayOf(edges[cycle][0], edges[cycle][1])
        } else { // 入度 > 2 的情况
            val conflictEdge = edges[conflict]
            if (cycle >= 0) { // 如果同时也成环了, 找到出现入度为 2 的边的终点的父亲，
                intArrayOf(parent[conflictEdge[1]], conflictEdge[1])
            } else {
                conflictEdge
            }
        }
    }

    private fun merge(find: IntArray, x: Int, y: Int) {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var index = x
        while (find[index] != index) {
            index = find[index]
        }
        find[x] = index // compress path
        return index
    }
}


fun main() {
    Solution().findRedundantDirectedConnection(
        MultiDimensionArray.createTestData("[[3,1],[2,1],[4,2],[1,4]]")
    ).print()
    Solution().findRedundantDirectedConnection(
        MultiDimensionArray.createTestData("[[1,2],[1,3],[2,3]]")
    ).print()
    Solution().findRedundantDirectedConnection(
        MultiDimensionArray.createTestData("[[1,2],[2,3],[3,4],[4,1],[1,5]]")
    ).print()

}
