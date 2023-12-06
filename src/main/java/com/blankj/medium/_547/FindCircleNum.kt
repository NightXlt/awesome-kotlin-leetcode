package com.blankj.medium._547

class FindCircleNum {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        if (isConnected.isEmpty()) return 0
        val find = IntArray(isConnected.size + 1) { it }
        for (i in isConnected.indices) {
            for (j in 0..i) {
                if (isConnected[i][j] == 1) {
                    merge(find, i + 1, j + 1)
                }
            }
        }
        var count = 0
        for (i in 1..isConnected.size) {
            if (find[i] == i) {
                count++
            }
        }
        return count
    }

    private fun merge(find: IntArray, x: Int, y: Int) {
        val fx = find(find, x)
        val fy = find(find, y)
        // 注意是更换父亲的节点， 而不是 x 指过去， x 指过去， x 的父亲那一条链并没有更换。
        if (fx != fy) {
            find[fx] = fy
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var x = x
        while (find[x] != x) {
            x = find[x]
        }
        return x
    }
}