package com.blankj.easy._252

class Solution {
    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        if (intervals.isEmpty()) return true
        intervals.sortWith { i1, i2 ->
            i1[0] - i2[0]
        }
        for (i in 0..<intervals.lastIndex) {
            if (intervals[i + 1][0] < intervals[i][1]) {
                return false
            }
        }
        return true
    }
}