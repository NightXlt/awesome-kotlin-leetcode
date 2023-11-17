package com.blankj.medium._399

import com.blankj.ext.print


class Solution {
    fun calcEquation(
        equations: List<List<String>>,
        values: DoubleArray,
        queries: List<List<String>>
    ): DoubleArray {
        val equationsSize: Int = equations.size
        // 二倍的原因是每条边有两个元素, 尽管会有浪费但可以接受吧
        val unionFind = UnionFind(2 * equationsSize)
        val map = HashMap<String, Int>(2 * equationsSize)
        var id = 0
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        for (i in 0 until equationsSize) {
            val equation = equations[i]
            val (dividend, divisor) = equation
            if (dividend !in map) {
                map[dividend] = id
                id++
            }
            if (divisor !in map) {
                map[divisor] = id
                id++
            }
            unionFind.union(map[dividend]!!, map[divisor]!!, values[i])
        }
        // 第 2 步：做查询
        val res = DoubleArray(queries.size)
        for (i in queries.indices) {
            val (dividend, divisor) = queries[i]
            val id1 = map[dividend]
            val id2 = map[divisor]
            if (id1 == null || id2 == null) {
                res[i] = -1.0
                continue
            }
            res[i] = unionFind.calWeight(id1, id2)
        }
        return res
    }
}

private class UnionFind(n: Int) {
    var find = IntArray(n) { it }
    var weights = DoubleArray(n) { 1.0 }

    // 合并过程中需要进行权值计算
    fun union(x: Int, y: Int, weight: Double) {
        val fx = find(x)
        val fy = find(y)
        if (fx == fy) return
        find[fx] = fy
        weights[fx] = weights[y] * weight / weights[x]
    }

    // 路径压缩
    fun find(x: Int): Int {
        if (x != find[x]) {
            val originParentNode = find[x]
            find[x] = find(find[x])
            weights[x] *= weights[originParentNode]
        }

        return find[x]
    }

    fun calWeight(x: Int, y: Int): Double {
        val fx = find(x);
        val fy = find(y);
        return if (fx == fy) {
            weights[x] / weights[y]
        } else {
            -1.0
        }
    }
}

fun main() {
//    [["x1","x2"],["x2","x3"],["x1","x4"],["x2","x5"]]
    Solution().calcEquation(
        listOf(
            listOf("x1","x2"),
            listOf("x2","x3"),
            listOf("x1","x4"),
            listOf("x2","x5"),
        ),
        doubleArrayOf(3.0,0.5,3.4,5.6),
        listOf(
            listOf("x2", "x4"),
            listOf("x1", "x5"),
            listOf("x1", "x3"),
            listOf("x5", "x5"),
            listOf("x5", "x1"),
            listOf("x3", "x4"),
            listOf("x4", "x3"),
            listOf("x6", "x6"),
            listOf("x0", "x0")
        )
    ).print()
}