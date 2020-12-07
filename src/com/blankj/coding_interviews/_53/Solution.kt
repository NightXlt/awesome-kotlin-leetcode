package com.blankj.coding_interviews._53

import com.blankj.coding_interviews._004.print


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
class Solution {

    fun searchRange(nums: IntArray, target: Int): IntArray {
        val res = intArrayOf(-1, -1)
        if (nums.isEmpty()) {
            return res
        }
        res[0] = getFirst(nums, target)
        if (res[0] == -1) return res
        res[1] = getLast(nums, target, res[0])
        return res
    }

    private fun getFirst(nums: IntArray, target: Int): Int {
        var (left, right) = Pair(0, nums.lastIndex)
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

    private fun getLast(nums: IntArray, target: Int, left: Int): Int {
        var (left, right) = Pair(left, nums.lastIndex)
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
    Solution().searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8).print()
    Solution().searchRange(intArrayOf(1, 1, 1, 1), 1).print()
    Solution().searchRange(intArrayOf(1, 2, 3, 4), 1).print()
    Solution().searchRange(intArrayOf(1, 2, 3, 4), 5).print()
}
