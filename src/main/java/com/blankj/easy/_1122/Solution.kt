package com.blankj.easy._1122

import com.blankj.ext.print

class Solution {
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        val count = IntArray(1001)
        arr1.forEach { count[it]++ }
        var i = 0
        for (num in arr2) {
            while (count[num] != 0) {
                arr1[i++] = num
                count[num]--
            }
        }
        count.indices.filter { count[it] != 0 }
            .forEach { index ->
                repeat(count[index]) {
                    arr1[i++] = index
                }
            }
        return arr1
    }
}

fun main() {
    Solution().relativeSortArray(intArrayOf(2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19), intArrayOf(2, 1, 4, 3, 9, 6)).print()
}