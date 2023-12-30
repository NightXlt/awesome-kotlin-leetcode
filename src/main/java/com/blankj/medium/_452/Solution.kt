package com.blankj.medium._452

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        if (points.isEmpty()) return 0
        points.sortBy { it[1] }
        var res = 1
        var pos = points[0][1]
        points.forEach {
            if (it[0] > pos) {
                pos = it[1]
                res++
            }
        }
        return res
    }
}

fun main() {
    Solution().findMinArrowShots(MultiDimensionArray.createTestData("[[9,12],[1,10],[4,11],[8,12],[3,9],[6,9],[6,7]]")).print()
    Solution().findMinArrowShots(MultiDimensionArray.createTestData("[[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]")).print()
    Solution().findMinArrowShots(MultiDimensionArray.createTestData("[[10,16],[2,8],[1,6],[7,12]]")).print()
    Solution().findMinArrowShots(MultiDimensionArray.createTestData("[[1,2],[3,4],[5,6],[7,8]]")).print()
    Solution().findMinArrowShots(MultiDimensionArray.createTestData("[[1,2],[2,3],[3,4],[4,5]]")).print()
}