package com.blankj.coding_interviews._40

import com.blankj.ext.print
import kotlin.random.Random


class Solution {
    fun getLeastNumbers(arr: IntArray, k: Int): IntArray {
        if (arr.isEmpty() || k <= 0 || k > arr.size) return intArrayOf()
        var pivot = partition(arr, 0, arr.size - 1)
        while (pivot != k - 1) {
            pivot = if (pivot > k - 1) {
                randomPartition(arr, 0, pivot - 1)
            } else {
                randomPartition(arr, pivot + 1, arr.size - 1)
            }
        }
        return arr.copyOf(k)
    }

    private fun randomPartition(nums: IntArray, low: Int, high: Int): Int {
        val len = high - low + 1
        val index = low + (len * Random.nextFloat()).toInt()
        nums[low] = nums[index].also { nums[index] = nums[low] }
        return partition(nums, low, high)
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
