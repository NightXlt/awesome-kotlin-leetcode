package com.blankj.medium._1229

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import kotlin.math.max
import kotlin.math.min

// https://leetcode.ca/2019-04-12-1229-Meeting-Scheduler/

class Solution {
    fun minAvailableDuration(
        slots1: Array<IntArray>,
        slots2: Array<IntArray>,
        duration: Int
    ): List<Int> {
        if (slots1.isEmpty() || slots2.isEmpty()) return emptyList()
        var index1 = 0
        var index2 = 0
        slots1.sortBy { it[0] }
        slots2.sortBy { it[0] }
        while (index1 < slots1.size && index2 < slots2.size) {
            val start = max(slots1[index1][0], slots2[index2][0])
            val end = min(slots1[index1][1], slots2[index2][1])
            if (start + duration <= end) {
                return listOf(start, start + duration)
            }
            if (slots1[index1][1] <= slots2[index2][1]) {
                index1++
            } else {
                index2++
            }
        }
        return emptyList()
    }
}

fun main() {
    /**
     * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
     * Output: [60,68]
     **/
    Solution().minAvailableDuration(
        MultiDimensionArray.createTestData("[[10,50],[60,120],[140,210]]"),
        MultiDimensionArray.createTestData("[[0,15],[60,70]]"),
        8
    ).print()

    Solution().minAvailableDuration(
        MultiDimensionArray.createTestData("[[10,50],[60,120],[140,210]]"),
        MultiDimensionArray.createTestData("[[0,15],[60,70]]"),
        12
    ).print()
}