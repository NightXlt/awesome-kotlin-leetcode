package com.blankj.coding_interviews._51

import com.blankj.coding_interviews._004.print


class Solution {

    fun reversePairs(nums: IntArray?): Int {
        if (nums == null || nums.isNotEmpty()) return 0
        val copy = IntArray(nums.size)
        return mergeSort(nums, copy, 0, nums.size - 1)
    }

    private fun mergeSort(nums: IntArray, copy: IntArray, low: Int, high: Int): Int {
        if (low >= high) return 0
        val mid = (low + high) shr 1 // 留意数组长度，其中 low + high是否可能溢出
        val leftReversePairs = mergeSort(nums, copy, low, mid)
        val rightReversePairs = mergeSort(nums, copy, mid + 1, high)
        val midReversePairs = merge(nums, copy, low, mid, high)
        return leftReversePairs + rightReversePairs + midReversePairs
    }

    private fun merge(nums: IntArray, copy: IntArray, low: Int, mid: Int, high: Int): Int {
        for (i in low..high) {
            copy[i] = nums[i]
        }
        var i = mid
        var j = high
        var k = high
        var count = 0
        while (i >= low && j > mid) {
            if (copy[i] > copy[j]) {
                count += j - mid
                nums[k--] = copy[i--]
            } else {
                nums[k--] = copy[j--]
            }
        }
        while (i >= low) {
            nums[k--] = copy[i--]
        }
        while (j > mid) {
            nums[k--] = copy[j--]
        }
        return count
    }
}

fun main() {
    Solution().reversePairs(null).print()
    Solution().reversePairs(intArrayOf(1)).print()
    Solution().reversePairs(intArrayOf(2, 1)).print()
    Solution().reversePairs(intArrayOf(7, 5, 6, 4)).print()
    Solution().reversePairs(intArrayOf(7, 7, 6, 4)).print()
    Solution().reversePairs(intArrayOf(7, 6, 5, 4)).print()
    Solution().reversePairs(intArrayOf(1, 2, 3, 4)).print()
}
