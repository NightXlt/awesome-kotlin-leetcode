package com.blankj.medium._1029

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun twoCitySchedCost(costs: Array<IntArray>): Int {
        costs.sortBy {
            it[0] - it[1]
        }
        val halfLength = costs.size / 2
        return costs.foldIndexed(0) { index, acc, ints ->
            if (index < halfLength) acc + ints[0] else acc + ints[1]
        }
    }
}

fun main() {
    Solution().twoCitySchedCost(
        MultiDimensionArray.createTestData("[[10,20],[30,200],[400,50],[30,20]]")
    ).print()
}