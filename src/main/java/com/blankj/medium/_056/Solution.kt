package com.blankj.medium._056

class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.isEmpty()) return emptyArray()
        intervals.sortWith { i1, i2 ->
            i1[0] - i2[0]
        }
        var i = 0
        val res = mutableListOf<IntArray>()
        while (i < intervals.size) {
            val temp = intervals[i]
            var j = i + 1
            while (j < intervals.size && intervals[j][0] <= temp[1]) {
                temp[1] = temp[1].coerceAtLeast(intervals[j][1])
                j++
            }
            res.add(temp)
            i = j
        }
        return res.toTypedArray()
    }
}