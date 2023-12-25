package com.blankj.google

import com.blankj.ext.print

class CountTime {

    fun countTime(time: String): Int {
        return count(time.substring(0, 2), 24) * count(time.substring(3), 60)
    }

    fun count(time: String, limit: Int): Int {
        var count = 0
        for (i in 0..<limit) {
            val high = i / 10
            val low = i % 10
            if ((time[0] == '?' || time[0] - '0' == high) &&
                (time[1] == '?' || time[1] - '0' == low)
            ) {
                count++
            }
        }
        return count
    }
}

fun main() {
    CountTime().countTime("?5:00").print()
}