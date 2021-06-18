package com.blankj.sort

import com.blankj.coding_interviews._004.print
import com.sun.istack.internal.NotNull
import kotlin.random.Random

/**
 *  Time: O(nlogn)
 *  Worst: O(n^2), ordered array, pivot is max/min
 *  Memory: O(logn) --- Stack space
 *  Stable: No
 *  Method: Partitioning
 */

class QuickSort {

    fun sort(data: IntArray?) {
        data ?: return
        quickSort(data, 0, data.lastIndex)
    }

    private fun randomPartition(@NotNull data: IntArray, length: Int, left: Int, right: Int): Int {
        if (length < 0 || left < 0 || right < 0) throw IllegalArgumentException("Illegal argument")
        val index = left + (length * Random.nextFloat()).toInt()
        swap(data, index, right)
        return partition(left, right, data)
    }

    private fun partition(left: Int, right: Int, data: IntArray): Int {
        var small = left - 1
        for (index in left until right) {
            if (data[index] < data[right]) {
                ++small
                if (small != index) {
                    swap(data, index, small)
                }
            }
        }
        ++small
        swap(data, small, right)
        return small
    }

    private fun quickSort(data: IntArray?, left: Int, right: Int) {
        if (data == null) return
        if (left < right) {
            val p = randomPartition(data, right - left + 1, left, right)
            quickSort(data, left, p - 1)
            quickSort(data, p + 1, right)
        }
    }

    private fun swap(data: IntArray, start: Int, end: Int) {
        data[start] = data[end].also { data[end] = data[start] }
    }

}

fun main() {
    var data = intArrayOf(5, 4, 3, 2, 1)
    QuickSort().sort(data)
    data.print()
    data = intArrayOf(5, 2, 3, 4, 1)
    QuickSort().sort(data)
    data.print()
}