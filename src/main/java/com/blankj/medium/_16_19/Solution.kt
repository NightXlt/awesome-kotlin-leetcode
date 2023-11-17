package com.blankj.medium._16_19

import java.util.*

class Solution {
    fun dfs(land: Array<IntArray>, row: Int, col: Int): Int {
        if (row < 0 || col < 0 || row >= land.size || col >= land[row].size || land[row][col] != 0) return 0
        land[row][col] = -1
        var count = 1
        count += dfs(land, row, col + 1)
        count += dfs(land, row, col - 1)
        count += dfs(land, row - 1, col)
        count += dfs(land, row + 1, col)
        count += dfs(land, row - 1, col + 1)
        count += dfs(land, row - 1, col - 1)
        count += dfs(land, row + 1, col + 1)
        count += dfs(land, row + 1, col - 1)
        return count
    }

    fun pondSizes(land: Array<IntArray>): IntArray {
        if (land.isEmpty()) return intArrayOf()
        var count: Int
        val queue = PriorityQueue<Int>()
        for (row in land.indices) {
            for (col in land[row].indices) {
                if (land[row][col] == 0) {
                    count = dfs(land, row, col)
                    queue.add(count)
                }

            }
        }
        return IntArray(queue.size) { queue.poll() }
    }

}
