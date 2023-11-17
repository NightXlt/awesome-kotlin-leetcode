package com.blankj.coding_interviews._57


class Solution {
    fun findContinuousSequence(target: Int): Array<IntArray> {
        if (target <= 0) return arrayOf()
        val res = mutableListOf<IntArray>()
        var small = 1
        var big = 2
        val mid = (1 + target) shr 1
        var curSum = small + big
        while (small < mid) {
            if (curSum == target) {
                res.add((small..big).toIntArray())
            }
            while (curSum > target && small < mid) {
                curSum -= small
                small++
                if (curSum == target) {
                    res.add((small..big).toIntArray())
                }
            }
            big++
            curSum += big
        }
        return res.toTypedArray()
    }

    private fun IntRange.toIntArray(): IntArray {
        return IntArray(last - first + 1) {
            return@IntArray start + it
        }
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {
        if (nums.size <= 1) return intArrayOf()
        var low = 0
        var high = nums.size - 1
        var sum: Int
        while (low < high) {
            sum = nums[low] + nums[high]
            when {
                sum == target ->
                    return intArrayOf(nums[low], nums[high])
                sum < target -> low++
                sum > target -> high--
            }
        }
        return intArrayOf()
    }
}

fun main() {
//    Solution().findContinuousSequence(9).print()
//    Solution().findContinuousSequence(15).print()
    Solution().twoSum(intArrayOf(2, 7, 11, 15), 9)
    Solution().twoSum(intArrayOf(10, 26, 30, 31, 47, 60), 40)
}