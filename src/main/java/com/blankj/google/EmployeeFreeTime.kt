package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.Interval

class EmployeeFreeTime {
    fun employeeFreeTime(schedule: ArrayList<ArrayList<Interval>>): ArrayList<Interval> {
        val open = -1
        val closed = 1
        // flatten: 把二维列表打平
        // flatMap：先对元素做 map, 做进行 flatten. 因此 flatMap 的返回值需要是一个 Iterable 接口
        val events = schedule.asSequence().flatten().flatMap {
            listOf(intArrayOf(it.start, open), intArrayOf(it.end, closed))
        }.toMutableList()
        events.sortWith(compareBy({ it[0] }, { it[1] }))
        val res = arrayListOf<Interval>()
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