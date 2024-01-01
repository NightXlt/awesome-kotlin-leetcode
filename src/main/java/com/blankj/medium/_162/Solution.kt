package com.blankj.medium._162

import com.blankj.ext.print

class Solution {
    fun findPeakElement(arr: IntArray): Int {
        var left = 0
        var right = arr.lastIndex
        while (left < right) {
            val mid = (left + right) shr 1
            if (arr[mid] > arr[mid + 1]) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return right
    }
}

fun main() {
    Solution().findPeakElement(intArrayOf(1)).print()
    Solution().findPeakElement(intArrayOf(0, 1)).print()
    Solution().findPeakElement(intArrayOf(1,0)).print()
}