package com.blankj.hard._135

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun candy(ratings: IntArray): Int {
        val left = IntArray(ratings.size)
        left[0] = 1
        for (i in 1..<ratings.size) {
            left[i] = if (ratings[i] > ratings[i - 1]) {
                left[i - 1] + 1
            } else {
                1
            }
        }
        var right = 1
        var res = max(left.last(), right)
        for (i in ratings.lastIndex - 1 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                right++
            } else {
                right = 1
            }
            res += max(right, left[i])
        }
        return res
    }
}

fun main() {
    Solution().candy(intArrayOf(1, 0, 2)).print()
    Solution().candy(intArrayOf(1, 2, 2)).print()
}