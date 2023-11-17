package com.blankj.coding_interviews._45

import com.blankj.ext.print


class Solution {
    fun minNumber(nums: IntArray): String {
        if (nums.isEmpty()) return ""
        val stringNums = Array(nums.size) {
            return@Array nums[it].toString()
        }
        quickSort(stringNums, 0, stringNums.size - 1)
        return stringNums.joinToString(separator = "")
    }

    private fun quickSort(nums: Array<String>, low: Int, high: Int) {
        if (low >= high) return
        val pivot = partition(nums, low, high)
        quickSort(nums, low, pivot - 1)
        quickSort(nums, pivot + 1, high)
    }

    private fun partition(nums: Array<String>, low: Int, high: Int): Int {
        var low = low
        var high = high
        val pivot = nums[low]
        while (low < high) {
            while (low < high && nums[high] + pivot > pivot + nums[high]) {
                high--
            }
            nums[low] = nums[high]
            while (low < high && nums[low] + pivot <= pivot + nums[low]) {
                low++
            }
            nums[high] = nums[low]
        }
        nums[low] = pivot
        return low
    }
   /* fun minNumber(nums: IntArray): String? {
        if (nums.isEmpty()) return ""
        val stringNums = Array(nums.size) {
            return@Array nums[it].toString()
        }
        Arrays.sort(stringNums, { x, y -> (x + y).compareTo(y + x) })
            return stringNums.joinToString(separator = "")
    }*/
}

fun main() {
    Solution().minNumber(intArrayOf(3, 30, 34, 5, 9)).print()
    Solution().minNumber(intArrayOf(10, 2)).print()
    Solution().minNumber(intArrayOf()).print()
}
