package com.blankj.coding_interviews._011

import com.blankj.coding_interviews._004.print

class Solution {
    fun minArray(numbers: IntArray): Int {
        var low = 0
        var high = numbers.size - 1
        if (numbers[low] < numbers[high]) return numbers[low]
        while (low < high) {
            val m = low + ((high - low) shr 1)
            when {
                numbers[m] > numbers[high] -> low = m + 1
                numbers[m] < numbers[low] -> high = m
                else -> high--
            }
        }
        return numbers[low]
    }
}

fun main() {
    Solution().minArray(intArrayOf(3, 4, 5, 1, 2)).print() // spin array
    Solution().minArray(intArrayOf(0, 1, 2, 3)).print()  // increment array
    Solution().minArray(intArrayOf(1, 1, 1, 0, 1)).print() // array include redundant number
    Solution().minArray(intArrayOf(1)).print() // single number
}
