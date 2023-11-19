package com.blankj.medium._2768

import com.blankj.ext.print

class Solution {
    fun countBlackBlocks(
        m: Int,
        n: Int,
        coordinates: Array<IntArray>
    ): LongArray {
        val map = mutableMapOf<Pair<Int, Int>, Int>()
        val dirs = arrayOf(
            intArrayOf(0, 0),
            intArrayOf(-1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, -1),
        )
        for (coordinate in coordinates) {
            for (dir in dirs) {
                val x = coordinate[0] + dir[0]
                val y = coordinate[1] + dir[1]
                val pair = x to y
                map[pair] = map.getOrDefault(pair, 0) + 1
            }
        }
        val res = LongArray(5)
        map.keys.filter { (x, y) ->
            x in 0..<m && y in 0..<n
        }.forEach {
            res[map.getValue(it)]++
        }
        res[0] = (m - 1) * (n - 1) - res.sum()
        return res
    }
}

fun main() {
    (1 to 2).hashCode().print()
    (1 to 2).hashCode().print()
    (3 to 4).hashCode().print()
}