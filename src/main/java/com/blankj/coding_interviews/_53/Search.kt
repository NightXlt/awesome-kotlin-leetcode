package com.blankj.coding_interviews._53

import com.blankj.ext.print


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Search {

    fun search(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) {
            return 0
        }
        val firstIndex = getFirst(nums, target)
        if (firstIndex == -1) return 0 // this is incompatible with return calculation (secondIndex - firstIndex + 1)
        val secondIndex = getLast(nums, target, firstIndex)
        return secondIndex - firstIndex + 1 // must add 1
    }

    private fun getFirst(nums: IntArray, target: Int): Int {
        var (left, right) = 0 to nums.lastIndex
        while (left <= right) {
            val mid = (left + right) shr 1
            when {
                nums[mid] == target -> {
                    if (mid == 0 || nums[mid - 1] != target) {
                        return mid
                    } else {
                        right = mid - 1
                    }
                }
                nums[mid] > target -> {
                    right = mid - 1
                }
                else ->
                    left = mid + 1
            }
        }
        return -1
    }

    private fun getLast(nums: IntArray, target: Int, firstIndex: Int): Int {
        var (left, right) = firstIndex to nums.lastIndex // 把左边界作为起始遍历的左边界, 减少重复遍历
        while (left <= right) {
            val mid = (left + right) shr 1
            when {
                nums[mid] == target -> {
                    if (mid == nums.lastIndex || nums[mid + 1] != target) {
                        return mid
                    } else {
                        left = mid + 1 // notice: here is mid + 1，find the second part
                    }
                }
                nums[mid] > target -> {
                    right = mid - 1
                }
                else ->
                    left = mid + 1
            }
        }
        return -1
    }
}

fun main() {
    Search().search(intArrayOf(1), 1).print() // one num
    Search().search(intArrayOf(5, 7, 7, 8, 8, 10), 8).print() // in array
    Search().search(intArrayOf(1, 2, 3, 4), 1).print() // min values
    Search().search(intArrayOf(1, 2, 3, 4), 4).print() // max values
    Search().search(intArrayOf(1, 1, 1, 1), 1).print()  // redundant num
    Search().search(intArrayOf(1, 2, 3, 4), 5).print() // not in array
}
