package com.blankj.medium._729

import com.blankj.ext.print
import java.util.*

class MyCalendar() {

    val map = TreeMap<Int, Int>()

    fun book(start: Int, end: Int): Boolean {
        var event = map.floorEntry(start)
        if (event != null && event.value > start) {
            return false
        }
        event = map.ceilingEntry(start)
        if (event != null && end > event.key) {
            return false
        }
        map[start] = end
        return true
    }

}

fun main() {
    val myCalendar = MyCalendar()
    myCalendar.book(10, 20).print()
    myCalendar.book(15, 25).print()
    myCalendar.book(20, 30).print()
}
