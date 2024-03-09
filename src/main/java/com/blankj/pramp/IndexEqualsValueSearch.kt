package com.blankj.pramp

import com.blankj.ext.print

class IndexEqualsValueSearch {

    fun indexEqualsValueSearch(arr: IntArray): Int {
        var low = 0
        var high = arr.lastIndex
        while (low <= high) {
            val mid = low + ((high - low) shr 1)
            when {
                arr[mid] == mid && (mid == 0 || arr[mid - 1] < mid - 1) -> return mid
                arr[mid] < mid -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return -1
    }
}

fun main() {
    IndexEqualsValueSearch().indexEqualsValueSearch(intArrayOf(-8, 0, 2, 5)).print()
    IndexEqualsValueSearch().indexEqualsValueSearch(intArrayOf(1, 0, 3, 6)).print()
}