package com.blankj.coding_interviews._57

import com.blankj.coding_interviews._004.print


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
}

fun main() {
    Solution().findContinuousSequence(9).print()
    Solution().findContinuousSequence(15).print()
}