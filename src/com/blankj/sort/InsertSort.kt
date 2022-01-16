package com.blankj.sort

import com.blankj.ext.print

/**
 *  Time: O(n^2)
 *  Memory: O(1)
 *  Stable: Y
 *  Method: Exchanging
 */

class InsertSort {

    fun insertSort(nums: IntArray?) {
        nums ?: return
        var cur: Int
        for (i in 1..nums.lastIndex) {
            cur = nums[i]
            var j = i - 1
            while (j >= 0 && nums[j] > cur) {
                nums[j + 1] = nums[j]
                j--
            }
            nums[j + 1] = cur
        }
    }
}

fun main() {
    var nums = intArrayOf(5, 4, 3, 2, 1)
    InsertSort().insertSort(nums)
    nums.print()
    nums = intArrayOf(5, 4, 2, 3, 1)
    InsertSort().insertSort(nums)
    nums.print()
    nums = intArrayOf(1)
    InsertSort().insertSort(nums)
    nums.print()
}