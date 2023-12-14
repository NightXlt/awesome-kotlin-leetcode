package com.blankj.medium._852

class Solution {
    fun peakIndexInMountainArray(arr: IntArray): Int {
        var left = 1
        var right = arr.lastIndex - 1
        while (left <= right) {
            val mid = (left + right) shr 1
            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) return mid
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return -1
    }
}
