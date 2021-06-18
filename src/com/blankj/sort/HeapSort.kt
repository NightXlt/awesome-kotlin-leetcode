package com.blankj.sort

import com.blankj.coding_interviews._004.print

/**
 *  Time: O(nlogn)
 *  Worst: O(nlogn)
 *  Memory: O(1)
 *  Stable: N
 *  Method: Selection
 */

class HeapSort {

    fun sortArray(nums: IntArray): IntArray? {
        heapSort(nums)
        return nums
    }

    private fun heapSort(nums: IntArray) {
        var len = nums.size - 1
        buildMaxHeap(nums, len)
        for (i in len downTo 1) {
            swap(nums, i, 0)
            len -= 1
            maxHeapify(nums, 0, len)
        }
    }

    private fun buildMaxHeap(nums: IntArray, len: Int) {
        for (i in len / 2 downTo 0) {
            maxHeapify(nums, i, len)
        }
    }

    private fun maxHeapify(nums: IntArray, i: Int, len: Int) {
        var i = i
        while ((i shl 1) + 1 <= len) {
            val leftChild = (i shl 1) + 1
            val rightChild = (i shl 1) + 2
            var large: Int
            large = if (leftChild <= len && nums[leftChild] > nums[i]) {
                leftChild
            } else {
                i
            }
            if (rightChild <= len && nums[rightChild] > nums[large]) {
                large = rightChild
            }
            if (large != i) {
                swap(nums, i, large)
                i = large
            } else {
                break
            }
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

}

fun main() {
    var nums = intArrayOf(5, 4, 3, 2, 1)
    HeapSort().sortArray(nums)
    nums.print()
    nums = intArrayOf(5, 4, 2, 3, 1)
    HeapSort().sortArray(nums)
    nums.print()
    nums = intArrayOf(1)
    HeapSort().sortArray(nums)
    nums.print()
}