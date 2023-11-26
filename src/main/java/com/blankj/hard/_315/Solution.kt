package com.blankj.hard._315

import com.blankj.ext.print

class Solution {
    var copy = intArrayOf()
    var res = intArrayOf()
    var indexes = intArrayOf()
    var copyIndexes = intArrayOf()

    fun countSmaller(nums: IntArray): List<Int> {
        if (nums.isEmpty()) return emptyList()
        copy = IntArray(nums.size)
        res = IntArray(nums.size)
        indexes = IntArray(nums.size)
        copyIndexes = IntArray(nums.size)
        for (i in nums.indices) {
            indexes[i] = i
        }
        mergeSort(nums, 0, nums.size - 1)
        return res.toList()
    }

    private fun mergeSort(
        nums: IntArray,

        low: Int,
        high: Int
    ) {
        if (low >= high) return
        val mid = (low + high) shr 1 // 留意数组长度，其中 low + high是否可能溢出
        mergeSort(nums, low, mid)
        mergeSort(nums, mid + 1, high)
        merge(nums, low, mid, high)
    }

    private fun merge(nums: IntArray, low: Int, mid: Int, high: Int) {
        for (i in low..high) {
            copy[i] = nums[i]
        }
        var i = low
        var j = mid + 1
        var k = low
        while (i <= mid && j <= high) {
            if (copy[i] <= copy[j]) {
                copyIndexes[k] = indexes[i]
                res[indexes[i]] += (j - mid - 1)
                nums[k++] = copy[i++]
            } else {
                copyIndexes[k] = indexes[j]
                nums[k++] = copy[j++]
            }
        }
        while (i <= mid) {
            copyIndexes[k] = indexes[i]
            res[indexes[i]] += (j - mid - 1)
            nums[k++] = copy[i++]
        }
        while (j <= high) {
            copyIndexes[k] = indexes[j]
            nums[k++] = copy[j++]
        }
        for (i in low..high) {
            indexes[i] = copyIndexes[i]
        }
    }
}

fun main() {
    Solution().countSmaller(intArrayOf(5, 2, 6, 1)).print()
}
