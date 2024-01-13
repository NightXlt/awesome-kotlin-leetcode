package com.blankj.medium._1146

import java.util.TreeMap

class SnapshotArray(length: Int) {

    val arr = MutableList<TreeMap<Int, Int>>(length) { TreeMap() }

    var snap = 0
    fun set(index: Int, `val`: Int) {
        if (index !in arr.indices) error("Illegal index access: $index")
        arr[index][snap] = `val`
    }

    fun snap(): Int {
        return snap++
    }

    fun get(index: Int, snap_id: Int): Int {
        if (index !in arr.indices) error("Illegal index access: $index")
        val key = arr[index].floorKey(snap_id)
        return if (key == null) 0 else arr[index].getValue(key)
    }

}