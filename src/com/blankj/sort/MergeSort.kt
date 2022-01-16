package com.blankj.sort

import com.blankj.ext.print

/**
 *  Time: O(nlogn)
 *  Worst: O(nlogn)
 *  Memory: O(n)
 *  Stable: Y
 *  Method: Divide and Conquer
 */
class MergeSort {

    private fun mergeSort(
            arr: IntArray,
            temp: IntArray,
            start: Int,
            end: Int
    ) {
        if (start >= end) return
        val len = end - start
        val mid = start + (len shr 1)
        mergeSort(arr, temp, start, mid)
        mergeSort(arr, temp, mid + 1, end)
        merge(start, mid, end, temp, arr)
    }

    private fun merge(
            start: Int,
            mid: Int,
            end: Int,
            temp: IntArray,
            arr: IntArray
    ) {
        var left = start
        var right = mid + 1
        var i = start
        while (left <= mid && right <= end) {
            // sign of equal keeps stability
            temp[i++] = if (arr[left] <= arr[right]) {
                arr[left++]
            } else {
                arr[right++]
            }
        }
        while (left <= mid) temp[i++] = arr[left++]
        while (right <= end) temp[i++] = arr[right++]
        for (j in start..end) {
            arr[j] = temp[j]
        }
    }

    fun sort(arr: IntArray?) {
        arr ?: return
        val len = arr.size
        val temp = IntArray(len)
        mergeSort(arr, temp, 0, len - 1)
    }
}

fun main() {
    var arr = intArrayOf(5, 4, 3, 2, 1)
    val mergeSort = MergeSort()
    mergeSort.sort(arr)
    arr.print()
    arr = intArrayOf(1)
    mergeSort.sort(arr)
    arr.print()
    arr = intArrayOf(1, 2, 3, 4, 5)
    mergeSort.sort(arr)
    arr.print()
}