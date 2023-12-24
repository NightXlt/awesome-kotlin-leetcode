package com.blankj.google

import java.util.*


 class RangeModule {
    private var intervals = TreeMap<Int, Int>()
    fun addRange(s: Int, e: Int) { // s: start, e: end
        // find overlap ranges, calc merged range, clear overlapped ranges, insert merged range
        var s = s
        var e = e
        val L = intervals.floorEntry(s) // left possible overlap entry
        val R = intervals.floorEntry(e) // right possible overlap entry
        if (L != null && L.value >= s) s = L.key // update overlap start
        if (R != null && R.value > e) e = R.value // update overlap end
        intervals.subMap(s, e).clear() // clear all overlapped entries
        intervals[s] = e // save final merged entry
    }

    fun queryRange(s: Int, e: Int): Boolean {
        val L = intervals.floorEntry(s)
        return L != null && L.value >= e // if there exist a range: start <+ s, end >= e
    }

    fun removeRange(s: Int, e: Int) {
        val L = intervals.floorEntry(s) // left possible overlap entry
        val R = intervals.floorEntry(e) // right possible overlap entry
        if (L != null && L.value > s) intervals[L.key] = s // after removal, if anything left
        if (R != null && R.value > e) intervals[e] = R.value // after removal, if anything left
        intervals.subMap(s, e).clear() // removal
    }
}

fun main() {
    val rangeModule = RangeModule()
    rangeModule.addRange(10, 20)
    rangeModule.removeRange(14, 16)
    rangeModule.queryRange(10, 14)
}