package com.blankj.medium._120

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray
import kotlin.math.min


class Solution {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        if (triangle.isEmpty()) return 0
        val dp = IntArray(triangle.size)
        dp[0] = triangle[0][0]
        for (i in 1 until triangle.size) {
            for (j in triangle[i].lastIndex downTo 0) {
                when {
                    j == 0 -> {
                        dp[0] += triangle[i][0]
                    }
                    j < triangle[i].size - 1 -> {
                        dp[j] = min(dp[j - 1], dp[j]) + triangle[i][j]
                    }
                    else -> {
                        dp[j] = dp[j - 1] + triangle[i][j]
                    }
                }
            }
        }
        return dp.minOrNull() ?: 0
    }
}

fun main() {
    Solution().minimumTotal(
        MultiDimensionArray.createTestData("[[2],[3,4],[6,5,7],[4,1,8,3]]").toList().map {
            it.toList()
        }
    ).print()

    Solution().minimumTotal(
        MultiDimensionArray.createTestData("[[-10]]").toList().map {
            it.toList()
        }
    ).print()

}