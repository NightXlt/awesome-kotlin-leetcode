package com.blankj.sort

import com.blankj.ext.print
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

    private fun randomPartition(data: IntArray, left: Int, right: Int): Int {
        val length = right - left + 1
        val index = left + (length * Random.nextFloat()).toInt()
        swap(data, index, right)
        return partition(left, right, data)
    }

    private fun partition(left: Int, right: Int, data: IntArray): Int {
        var pivot = left - 1 // point num less than data[right]
        for (index in left until right) {
            if (data[index] >= data[right]) continue
            ++pivot
            if (pivot != index) {
                swap(data, index, pivot)
            }
        }
        ++pivot
        swap(data, pivot, right)
        return pivot
    }

    private fun quickSort(data: IntArray?, left: Int, right: Int) {
        if (data == null) return
        if (left < right) {
            val p = randomPartition(data, left, right)
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
    val quickSort = QuickSort()
    quickSort.sort(data)
    data.print()
    data = intArrayOf(5, 2, 3, 4, 1)
    quickSort.sort(data)
    data.print()
    data = intArrayOf(1)
    quickSort.sort(data)
    data.print()
}