package com.blankj.pramp

import com.blankj.ext.print

class FindBusiestPeriod {
    fun findBusiestPeriod(data: Array<IntArray>): Int {
        var maxV = 0
        var curV = 0
        var time = 0
        val n = data.size
        for (i in 0 until n) {
            if (data[i][2] == 1) {
                curV += data[i][1]
            } else {
                curV -= data[i][1]
            }
            if (i == n - 1 || data[i][0] != data[i + 1][0]) {
                if (curV > maxV) {
                    maxV = curV
                    time = data[i][0]
                }
            }
        }
        return time
    }
}

fun main() {
    val data = arrayOf(
        intArrayOf(1487799425, 14, 1),
        intArrayOf(1487799425, 4, 0),
        intArrayOf(1487799425, 2, 0),
        intArrayOf(1487800378, 10, 1),
        intArrayOf(1487801478, 18, 0),
        intArrayOf(1487801478, 18, 1),
        intArrayOf(1487901013, 1, 0),
        intArrayOf(1487901211, 7, 1),
        intArrayOf(1487901211, 7, 0)
    )
    FindBusiestPeriod().findBusiestPeriod(data).print()
}