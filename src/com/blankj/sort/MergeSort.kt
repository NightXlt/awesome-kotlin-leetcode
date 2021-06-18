package com.blankj.sort

import com.blankj.coding_interviews._004.print

/**
 *  Time: O(nlogn)
 *  Worst: O(nlogn)
 *  Memory: O(n)
 *  Stable: Y
 *  Method: Divide and Conquer
 */
class MergeSort {

    private fun mergeSort(arr: IntArray, result: IntArray, start: Int, end: Int) {
        if (start >= end) return
        val len = end - start
        val mid = start + (len shr 1)
        mergeSort(arr, result, start, mid)
        mergeSort(arr, result, mid + 1, end)
        merge(start, mid, end, result, arr)
    }

    private fun merge(start: Int, mid: Int, end: Int, result: IntArray, arr: IntArray) {
        var left = start
        var right = mid + 1
        var i = start
        while (left <= mid && right <= end) {
            result[i++] = if (arr[left] < arr[right]) {
                arr[left++]
            } else {
                arr[right++]
            }
        }
        while (left <= mid) result[i++] = arr[left++]
        while (right <= end) result[i++] = arr[right++]
        i = start
        while (i <= end) {
            arr[i] = result[i]
            i++
        }
    }

    fun sort(arr: IntArray?) {
        arr ?: return
        val len = arr.size
        val result = IntArray(len)
        mergeSort(arr, result, 0, len - 1)
    }
}

fun main() {
    var arr = intArrayOf(5, 4, 3, 2, 1)
    MergeSort().sort(arr)
    arr.print()
}