package com.blankj.medium._969

import com.blankj.ext.print


class Solution {
    private fun flip(arr: IntArray, k: Int) {
        var left = 0
        var right = k - 1
        while (left < right) {
            arr[left] = arr[right].also { arr[right] = arr[left] }
            left++
            right--
        }
    }

    private fun findMax(arr: IntArray, lastIndex: Int): Int {
        var max = Int.MIN_VALUE
        var index = -1
        for (i in 0 until lastIndex) {
            if (arr[i] > max) {
                max = arr[i]
                index = i
            }
        }
        return index
    }

    fun pancakeSort(arr: IntArray): List<Int> {
        var lastIndex = arr.size
        var res = mutableListOf<Int>()
        while (lastIndex > 0) {
            val maxIndex = findMax(arr, lastIndex)
            flip(arr, maxIndex + 1)
            res.add(maxIndex + 1)
            flip(arr, lastIndex)
            res.add(lastIndex)
            lastIndex--
        }
        return res
    }
}

fun main() {
    Solution().pancakeSort(intArrayOf(3, 2, 4, 1)).print()
}