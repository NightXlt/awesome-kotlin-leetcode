package com.blankj.hard._305

class Solution {

    val dirs = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
        intArrayOf(1, 0),
    )

    fun numIslands2(m: Int, n: Int, positions: Array<IntArray>): List<Int> {
        if (positions.isEmpty()) return emptyList()
        val res = mutableListOf<Int>()
        val unionFind = UnionFind(m * n)
        val visited = BooleanArray(m * n)
        for ((row, col) in positions) {
            val index = row * n + col
            if (visited[index]) {
                res.add(unionFind.count)
                continue
            }
            visited[index] = true
            unionFind.addCount()
            for ((rowDiff, colDiff) in dirs) {
                val newRow = row + rowDiff
                val newCol = col + colDiff
                val newIndex = newRow * n + newCol
                if (isValidIndex(newRow, newCol, m, n)
                    && visited[newIndex]
                    && !unionFind.isConnected(index, newIndex)
                ) {
                    unionFind.union(index, newIndex)
                }
            }
            res.add(unionFind.count)
        }
        return res
    }

    private fun isValidIndex(row: Int, col: Int, m: Int, n: Int): Boolean {
        return row in 0..<m && col in 0..<n
    }

    class UnionFind(size: Int) {

        private val find = IntArray(size) { it }
        var count = 0

        fun addCount() {
            count++
        }

        fun find(x: Int): Int {
            if (x != find[x]) {
                return find(find[x])
            }
            return find[x]
        }

        fun isConnected(x: Int, y: Int): Boolean {
            return find(x) == find(y)
        }

        fun union(x: Int, y: Int) {
            val fx = find(x)
            val fy = find(y)
            if (fx == fy) return
            find[fx] = fy
            count--
        }
    }
}