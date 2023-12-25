package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.Interval

class EmployeeFreeTime {
    fun employeeFreeTime(schedule: ArrayList<ArrayList<Interval>>): ArrayList<Interval> {
        val events = mutableListOf<IntArray>()
        val open = -1
        val closed = 1
        schedule.flatten().forEach {
            events.add(intArrayOf(it.start, open))
            events.add(intArrayOf(it.end, closed))
        }
        events.sortWith(compareBy({ it[0] }, { it[1] }))
        var res = arrayListOf<Interval>()
        var prev = -1
        var balanced = 0
        for (event in events) {
            if (balanced == 0 && prev >= 0) {
                res.add(Interval(prev, event[0]))
            }
            balanced += event[1]
            prev = event[0]
        }
        return res
    }
}

fun main() {
    EmployeeFreeTime().employeeFreeTime(
        arrayListOf(
            arrayListOf(
                Interval(1, 2),
                Interval(5, 6)
            ),
            arrayListOf(
                Interval(1,3),
                Interval(4, 10),
            )
        )
    ).print()
}