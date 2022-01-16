package com.blankj.sort

import com.blankj.ext.print

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
            // 自顶向下得到一个最小堆,这样遍历数组即是有序的
            maxHeapify(nums, 0, len)
        }
    }

    private fun buildMaxHeap(nums: IntArray, len: Int) {
        // 自底向上平衡得到一个最大堆，
        for (i in len / 2 downTo 0) {
            maxHeapify(nums, i, len)
        }
    }

    /**
     * 维护 i..length 为一个最大堆
     */
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
                i = large // 交换后的子树 i 可能不平衡，需要再次以子树 i 递归维持平衡
            } else {
                break
            }
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        nums[i] = nums[j].also {
            nums[j] = nums[i]
        }
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