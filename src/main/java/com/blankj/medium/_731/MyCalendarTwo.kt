package com.blankj.medium._731

import java.util.TreeMap

class MyCalendarTwo() {

    val map = TreeMap<Int, Int>().withDefault { 0 }

    fun book(start: Int, end: Int): Boolean {
        map[start] = map.getValue(start) + 1
        map[end] = map.getValue(end) - 1
        var activeBookings = 0
        for (ending in map.values) {
            activeBookings += ending
            if (activeBookings > 2) {
                map[start] = map.getValue(start) - 1
                map[end] = map.getValue(end) + 1
                return false
            }
        }
        return true
    }

}