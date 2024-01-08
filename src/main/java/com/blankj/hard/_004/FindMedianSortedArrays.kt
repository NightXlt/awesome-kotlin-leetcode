package com.blankj.hard._004

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class FindMedianSortedArrays {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        if (nums1.size > nums2.size) return findMedianSortedArrays(nums2, nums1)
        val length1 = nums1.size
        val length2 = nums2.size
        var low = 0
        // 留意 i + j = total_len / 2
        // 之所以是 length，是因为想找到中位数后面一位数下标i,j，
        // 这样中位数根据数组的奇偶性可以得到是
        // 奇：max(nums1[i - 1], nums2[j - 1])
        // 偶：(min(nums1[i], nums2[j]) + max(nums1[i - 1], nums2[j - 1])) / 2
        var high = length1
        val halfLength = (length1 + length2 + 1) / 2
        while (low <= high) {
            val i = (low + high) / 2
            val j = halfLength - i
            when {
                i < high && nums1[i] < nums2[j - 1] -> {
                    low = i + 1
                }

                i > low && nums2[j] < nums1[i - 1] -> {
                    high = i - 1
                }

                else -> {
                    val maxLeftValue = when {
                        i == 0 -> nums2[j - 1]
                        j == 0 -> nums1[i - 1]
                        else -> max(nums1[i - 1], nums2[j - 1])
                    }
                    // length is odd, return directly
                    if ((length1 + length2) and 1 == 1) {
                        return maxLeftValue.toDouble()
                    }
                    // find right area min value
                    val minRightValue = when {
                        i == length1 -> nums2[j]
                        j == length2 -> nums1[i]
                        else -> min(nums1[i], nums2[j])
                    }
                    return (maxLeftValue + minRightValue) / 2.0
                }
            }
        }
        return 0.0
    }

    /**
     * 以找第 K 个最小值找到中位数
     *  - 长度为奇数，则中位数是第 length / 2 + 1 小的元素
     *  - 长度为偶数，则中位数是第 length / 2 小的元素 + length / 2 + 1 小的元素 的平均值
     *
     *  通过二分法去找到第 K 个元素
     *  记录 nums1, nums2 的访问索引为 i，j.
     *  每次让 i, j 分别加上 k / 2 - 1, 这样 i + j 的元素个数即为 k.
     *  如果 nums1[i + k / 2 - 1] < nums2[j + k / 2 - 1], 说明 第 K 小的元素不在 nums[0..i + k / 2 - 1]这块区间中，所以可以直接排除掉这块
     *  因为排除掉了 k / 2个元素，所以 k 也需要减去 k / 2.
     *  同理 nums1[i + k / 2 - 1] > nums2[j + k / 2 - 1] 也是一样需要排除的。
     *
     *  那么什么时候会找到元素呢？
     *  - 遍历完了一个数组如nums1，发现所有元素都是比第 k 个元素要小，那么第 k 个元素必然是出现在另一个数组nums2中， 就可以直接 nums[j + k - 1]取
     *  - k == 1, 表明要找第一个最小元素，直接比较两个数组当前索引的最小值。
     *
     *  自己再不理解，可以对照 柯基的图和 note 看下
     */
    fun findMedianSortedArraysWithKth(nums1: IntArray, nums2: IntArray): Double {
        val length = nums1.size + nums2.size
        if (length % 2 == 1) {
            return helper(nums1, 0, nums2, 0, length / 2 + 1)
        }
        return (helper(nums1, 0, nums2, 0, length / 2 + 1) +
                helper(nums1, 0, nums2, 0, length / 2)) / 2.0
    }

    private fun helper(nums1: IntArray, i: Int, nums2: IntArray, j: Int, k: Int): Double {
        if (i >= nums1.size) return nums2[j + k - 1].toDouble()
        if (j >= nums2.size) return nums1[i + k - 1].toDouble()
        if (k == 1) return min(nums1[i], nums2[j]).toDouble()
        val p1 = i + k / 2 - 1
        val p2 = j + k / 2 - 1
        // p1, p2可能会越界，越界时当做最大值兜底
        val mid1 = if (p1 >= nums1.size) Int.MAX_VALUE else nums1[p1]
        val mid2 = if (p2 >= nums2.size) Int.MAX_VALUE else nums2[p2]
        if (mid1 < mid2) {
            return helper(nums1, i + k / 2, nums2, j, k - k / 2)
        }
        return helper(nums1, i, nums2, j + k / 2, k - k / 2)
    }
}

fun main() {
    FindMedianSortedArrays().findMedianSortedArraysWithKth(
        intArrayOf(1, 2, 3, 4, 5),
        intArrayOf(6, 7, 8, 9)
    ).print()
    FindMedianSortedArrays().findMedianSortedArraysWithKth(
        intArrayOf(1, 2),
        intArrayOf(3, 4)
    ).print()
}