package com.blankj.medium._539

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun findMinDifference(timePoints: List<String>): Int {
        // if time is more than one day, means the next day minute must have same point
        if (timePoints.size > ONE_DAY_MINUTE) {
            return 0
        }
        val minuteFlag = BooleanArray(1440)
        for (time in timePoints) {
            val (hour, minute) = time.split(":")
            val min = hour.toInt() * 60 + minute.toInt()
            if (minuteFlag[min]) {
                return 0
            }
            minuteFlag[min] = true
        }
        return helper(minuteFlag)
    }

    private fun helper(minuteFlag: BooleanArray): Int {
        var minDiff = Int.MAX_VALUE
        var first = minuteFlag.lastIndex
        var last = -1
        var pre = -1
        minuteFlag.indices.filter { minuteFlag[it] }
            .forEach {
                if (pre >= 0) {
                    minDiff = min((it - pre), minDiff)
                }
                pre = it
                first = min(it, first)
                last = max(it, last)
            }
        return min(minDiff, first + ONE_DAY_MINUTE - last)
    }

    companion object {
        const val ONE_DAY_MINUTE = 60 * 24
    }
}

fun main() {
    Solution().findMinDifference(
        listOf("23:59", "00:00")
    ).print()
    Solution().findMinDifference(
        listOf("00:00","23:59","00:00")
    ).print()
}