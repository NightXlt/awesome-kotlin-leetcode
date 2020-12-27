package com.blankj.coding_interviews._40

import com.blankj.coding_interviews._004.print


class Solution {
    fun getLeastNumbers(arr: IntArray, k: Int): IntArray {
        if (arr.isEmpty() || k <= 0 || k > arr.size) return intArrayOf()
        var pivot = partition(arr, 0, arr.size - 1)
        while (pivot != k - 1) {
            pivot = if (pivot > k - 1) {
                partition(arr, 0, pivot - 1)
            } else {
                partition(arr, pivot + 1, arr.size - 1)
            }
        }
        return arr.copyOf(k)
    }

    private fun partition(nums: IntArray, low: Int, high: Int): Int {
        var low = low
        var high = high
        val pivot = nums[low]
        while (low < high) {
            while (low < high && nums[high] >= pivot) {
                high--
            }
            nums[low] = nums[high]
            while (low < high && nums[low] <= pivot) {
                low++
            }
            nums[high] = nums[low]
        }
        nums[low] = pivot
        return low
    }
}

fun main() {
    Solution().getLeastNumbers(intArrayOf(), 3).print()
    Solution().getLeastNumbers(intArrayOf(), -1).print()
    Solution().getLeastNumbers(intArrayOf(3, 2, 1), 3).print()
    Solution().getLeastNumbers(intArrayOf(0, 1, 2, 1), 3).print()
    Solution().getLeastNumbers(intArrayOf(1, 1, 1, 1, 1), 3).print()
}