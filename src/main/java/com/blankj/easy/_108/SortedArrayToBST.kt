package com.blankj.easy._108

import com.blankj.ext.print
import com.blankj.structure.TreeNode

class SortedArrayToBST {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        if (nums.isEmpty()) return null
        return convertArrayToBST(nums, 0, nums.lastIndex)
    }

    private fun convertArrayToBST(nums: IntArray, low: Int, high: Int): TreeNode? {
        if (high < low) return null
        val mid = low + ((high - low) shr 1)
        return TreeNode(nums[mid]).apply {
            left = convertArrayToBST(nums, low, mid - 1)
            right = convertArrayToBST(nums, mid + 1, high)
        }
    }
}

fun main() {
    SortedArrayToBST().sortedArrayToBST(intArrayOf(-10, -3, 0, 5, 9))?.print()
}