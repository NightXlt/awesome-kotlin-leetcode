package com.blankj.hard._732

import java.util.*

class MyCalendarThree() {

    val map = TreeMap<Int, Int>().withDefault { 0 }

    fun book(startTime: Int, endTime: Int): Int {
        map[startTime] = map.getValue(startTime) + 1
        map[endTime] = map.getValue(endTime) - 1
        var res = 0
        var activeBooking = 0
        for (ending in map.values) {
            activeBooking += ending
            if (activeBooking > res) {
                res = activeBooking
            }
        }
        return res
    }

}