package com.blankj.medium._912

import com.blankj.ext.print
import kotlin.random.Random

class Solution {
    fun sortArray(nums: IntArray): IntArray {
        quickSort(nums, 0, nums.lastIndex)
        return nums
    }

    private fun quickSort(nums: IntArray, left: Int, right: Int) {
        if (left < right) {
            val partition = randomPartition(nums, left, right)
            quickSort(nums, left, partition)
            quickSort(nums, partition + 1, right)
        }
    }

    private fun randomPartition(nums: IntArray, left: Int, right: Int): Int {
        val length = right - left + 1
        var index = left + (length * Random.nextFloat()).toInt()
        swap(nums, index, right)
        // partition point last element smaller than rightest element
        var pivot = left - 1
        for (i in left..<right) {
            if (nums[i] >= nums[right]) continue
            pivot++
            swap(nums, pivot, i)
        }
        pivot++
        swap(nums, pivot, right)
        return pivot
    }

    private fun swap(nums: IntArray, left: Int, right: Int) {
        if (left == right) return
        nums[right] = nums[left].also { nums[left] = nums[right] }
    }

}

fun main() {
    Solution().sortArray(intArrayOf(5,2,3,1)).print()
}