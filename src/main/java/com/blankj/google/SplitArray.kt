package com.blankj.google

import com.blankj.ext.print
import kotlin.math.max

class SplitArray {
    fun splitArray(nums: IntArray, k: Int): Int {
        val max = nums.max()
        val sum = nums.sum()
        var left = max(max, sum / k)
        var right = sum
        while (left <= right) {
            val mid = left + ((right - left) shr 1)
            if (check(nums, mid, k)) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }

    private fun check(nums: IntArray, maxSum: Int, k: Int): Boolean {
        var count = 1
        var sum = 0
        for (num in nums) {
            if (sum + num <= maxSum) {
                sum += num
                continue
            }
            if (count == k || num > maxSum) {
                return false
            }
            count++
            sum = num
        }
        return true
    }
}

fun main() {
    SplitArray().splitArray(intArrayOf(1, 4, 4), 3).print()
    SplitArray().splitArray(intArrayOf(7, 2, 5, 10, 8), 2).print()
    SplitArray().splitArray(intArrayOf(1, 2, 3, 4, 5), 2).print()
}