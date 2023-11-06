package com.blankj.medium._547

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        if (isConnected.isEmpty()) return 0
        val find = IntArray(isConnected.size + 1) { it }
        for (i in isConnected.indices) {
            for (j in 0 until i) {
                if (isConnected[i][j] == 1) {
                    merge(find, i + 1, j + 1)
                }
            }
        }
        var count = 0
        for (i in 1 until find.size) {
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
        var index = x
        while (find[index] != index) {
            index = find[index]
        }
        return index
    }
}
fun main() {
    Solution().findCircleNum(
        MultiDimensionArray.createTestData("[[1,1,0],[1,1,0],[0,0,1]]")
    ).print()
    Solution().findCircleNum(
        MultiDimensionArray.createTestData("[[1,0,0],[0,1,0],[0,0,1]]")
    ).print()
}