package com.blankj.medium._542

class Solution {
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val rows = mat.size
        val cols = mat[0].size
        val dists = Array(rows) { IntArray(cols) }
        val queue = ArrayDeque<IntArray>()
        repeat(rows) { i ->
            repeat(cols) { j ->
                if (mat[i][j] == 0) {
                    queue.add(intArrayOf(i, j))
                    dists[i][j] = 0
                } else {
                    dists[i][j] = Int.MAX_VALUE
                }
            }
        }
        val dirs = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
        )
        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            val dist = dists[pos[0]][pos[1]]
            for (dir in dirs) {
                val r = pos[0] + dir[0]
                val c = pos[1] + dir[1]
                if (r in mat.indices && c in mat[0].indices) {
                // 如果访问到非 0 节点统计其步调， 并记录最小值，然后加入到队列中
                    if (dists[r][c] > dist + 1) {
                        dists[r][c] = dist + 1
                        queue.add(intArrayOf(r, c))
                    }
                }
            }
        }
        return dists
    }
}

fun main() {

}