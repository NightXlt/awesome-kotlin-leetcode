package com.blankj.medium._2013

class DetectSquares() {

    private val rowToCol = mutableMapOf<Int, MutableMap<Int, Int>>()

    fun add(point: IntArray) {
        val (x, y) = point
        val colToCount = rowToCol.getOrPut(x) { mutableMapOf<Int, Int>() }
        colToCount.merge(y, 1) { a, b -> a + b }
    }

    fun count(point: IntArray): Int {
        val (x, y) = point
        var res = 0
        val colToCount = rowToCol.getOrDefault(x, mutableMapOf<Int, Int>())
        for (y1 in colToCount.keys) {
            if (y1 == y) continue
            val c1 = colToCount.getValue(y1)
            val len = y - y1
            val xRanges = intArrayOf(x - len, x + len)
            for (x1 in xRanges) {
                val colToCount1 = rowToCol.getOrDefault(x1, mutableMapOf<Int, Int>())
                val c2 = colToCount1.getOrDefault(y, 0)
                val c3 = colToCount1.getOrDefault(y1, 0)
                res += c1 * c2 * c3
            }
        }
        return res
    }

}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * var obj = DetectSquares()
 * obj.add(point)
 * var param_2 = obj.count(point)
 */