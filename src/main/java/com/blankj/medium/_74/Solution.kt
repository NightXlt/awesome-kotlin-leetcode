package com.blankj.medium._74

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false
        val rowIndex = searchFirstCol(matrix, target)
        if (rowIndex < 0) {
            return false
        }
        return searchRow(matrix[rowIndex], target)
    }

    private fun searchRow(nums: IntArray, target: Int): Boolean {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = left + ((right - left) shr 1)
            when {
                nums[mid] == target -> return true
                nums[mid] > target -> right = mid - 1
                else -> left = mid + 1
            }
        }
        return false
    }

    private fun searchFirstCol(matrix: Array<IntArray>, target: Int): Int {
        var left = -1
        var right = matrix.lastIndex
        while (left < right) {
            val mid = left + ((right - left + 1) shr 1)
            if (matrix[mid][0] > target) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        return left
    }
}

fun main() {
    Solution().searchMatrix(
        MultiDimensionArray.createTestData("[[1]]"),
        1
    ).print()
    Solution().searchMatrix(
        MultiDimensionArray.createTestData("[[1,3,5,7],[10,11,16,20],[23,30,34,60]]"),
        3
    ).print()
    Solution().searchMatrix(
        MultiDimensionArray.createTestData("[[1,3,5,7],[10,11,16,20],[23,30,34,60]]"),
        13
    ).print()
}