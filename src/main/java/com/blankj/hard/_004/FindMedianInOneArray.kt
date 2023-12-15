package com.blankj.hard._004

import com.blankj.ext.print
import kotlin.math.min

class FindMedianInOneArray {
    private var pivot = -1
    fun findMedianSortedArrays(nums1: IntArray, k: Int): Double {
        this.pivot = k
        if (nums1.size and 1 == 1) {
            return helper(nums1, 0, nums1.lastIndex, nums1.size / 2 + 1)
        }
        return (
                helper(
                    nums1,
                    0,
                    nums1.lastIndex,
                    nums1.size / 2 + 1
                ) + helper(
                    nums1,
                    0,
                    nums1.lastIndex,
                    nums1.size / 2
                )) / 2
    }

    private fun helper(nums1: IntArray, i: Int, j: Int, k: Int): Double {
        if (i >= pivot + 1) return nums1[j - k + 1].toDouble()
        if (j <= pivot) return nums1[i + k - 1].toDouble()
        if (k == 1) return min(nums1[i], nums1[j]).toDouble()
        val p = i + k / 2 - 1
        val q = j - k / 2 + 1
        val mid1 = if (p >= pivot + 1) Int.MAX_VALUE else nums1[p]
        val mid2 = if (q <= pivot) Int.MAX_VALUE else nums1[q]
        if (mid1 <= mid2) {
            return helper(nums1, p + 1, j, k - k / 2)
        } else {
            return helper(nums1, i, q - 1, k - k / 2)
        }
    }
}

fun main() {
    FindMedianInOneArray().findMedianSortedArrays(intArrayOf(1, 2, 3, 5, 4, 3, 2), 3).print()
    FindMedianInOneArray().findMedianSortedArrays(intArrayOf(1, 2, 3, 5, 4, 3, 2, 1), 3).print()
}