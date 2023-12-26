package com.blankj.medium._939

import java.util.*


class Solution {
    fun minAreaRect(points: Array<IntArray>): Int {
        val map = TreeMap<Int, MutableList<Int>>()
        points.forEach {
            map.getOrPut(it[0]) { mutableListOf() }
                .add(it[1])
        }
        val visited = mutableMapOf<Int, Int>()
        var res = Int.MAX_VALUE
        for ((x, row) in map) {
            row.sort()
            for (i in row.indices) {
                for (j in i + 1..<row.size) {
                    val y1 = row[i]
                    val y2 = row[j]
                    val hash = 31 * y1 + y2
                    if (hash in visited) {
                        res = res.coerceAtMost((x - visited.getValue(hash)) * (y2 - y1))
                    }
                    visited[hash] = x
                }
            }
        }
        return if (res == Int.MAX_VALUE) 0 else res
    }
}