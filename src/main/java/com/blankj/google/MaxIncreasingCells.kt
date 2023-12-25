package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import java.util.*
import kotlin.math.max


class MaxIncreasingCells {
    fun maxIncreasingCells(mat: Array<IntArray>): Int {
        val graph = TreeMap<Int, MutableList<IntArray>>()
        for (i in mat.indices) {
            for (j in mat.first().indices) {
                graph.getOrPut(mat[i][j]) { mutableListOf() }
                    .add(intArrayOf(i, j))
            }
        }
        val rowMax = IntArray(mat.size)
        val colMax = IntArray(mat.first().size)
        var res = 0
        for (positions in graph.values) {
            val mx = IntArray(positions.size)
            for (i in positions.indices) {
                mx[i] = max(rowMax[positions[i][0]], colMax[positions[i][1]]) + 1
                res = max(res, mx[i])
            }

            for (k in positions.indices) {
                val i = positions[k][0]
                val j = positions[k][1]
                rowMax[i] = max(mx[k], rowMax[i])
                colMax[j] = max(mx[k], colMax[j])
            }
        }
        return res
    }
}

fun main() {
    MaxIncreasingCells().maxIncreasingCells(
        MultiDimensionArray.createTestData("[[3,1],[3,4]]")
    ).print()
}
