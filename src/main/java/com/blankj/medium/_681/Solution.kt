package com.blankj.medium._681

import com.blankj.ext.print


class Solution {
    fun nextClosestTime(time: String): String {
        val timeSplit = time.split(":")
        val set = setOf(
            timeSplit[0][0],
            timeSplit[0][1],
            timeSplit[1][0],
            timeSplit[1][1],
        )
        val hour = timeSplit[0].toInt()
        val minute = timeSplit[1].toInt()
        val minutes = hour * 60 + minute
        val maxMinute = minutes + ONE_DAY_MINUTE
        for (i in minutes + 1..<maxMinute) {
            var h = ((i % ONE_DAY_MINUTE) / 60).toString()
            if (h.length == 1) {
                h = "0$h"
            }
            var m = ((i % ONE_DAY_MINUTE) % 60).toString()
            if (m.length == 1) {
                m = "0$m"
            }
            if (h.all { it in set } && m.all { it in set }) {
                return "$h:$m"
            }
        }
        return ""
    }

    companion object {
        const val ONE_DAY_MINUTE = 24 * 60

    }
}

fun main() {
    Solution().nextClosestTime("19:34").print()
}