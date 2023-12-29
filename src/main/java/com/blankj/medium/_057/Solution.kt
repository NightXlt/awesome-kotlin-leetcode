package com.blankj.medium._057

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val res = mutableListOf<IntArray>()
        var i = 0
        while (i < intervals.size) {
            if (intervals[i][1] < newInterval[0]) {
                res.add(intervals[i])
                i++
            } else {
                break
            }
        }
        while (i < intervals.size) {
            if (intervals[i][0] <= newInterval[1]) {
                newInterval[0] = min(newInterval[0], intervals[i][0])
                newInterval[1] = max(newInterval[1], intervals[i][1])
                i++
            } else {
                break
            }
        }
        res.add(newInterval)
        while (i < intervals.size) {
            res.add(intervals[i++])
        }
        return res.toTypedArray()
    }
}

    fun main() {
        Solution().insert(
            MultiDimensionArray.createTestData("[[1,5]]"),
            intArrayOf(6, 8)
        ).print()
        Solution().insert(
            MultiDimensionArray.createTestData("[[1,2],[3,5],[6,7],[8,10],[12,16]]"),
            intArrayOf(4, 8)
        ).print()
    }