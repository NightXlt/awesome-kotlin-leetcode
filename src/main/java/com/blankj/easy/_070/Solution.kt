package com.blankj.easy._070

import com.blankj.ext.print

class Solution {
    fun climbStairs(n: Int): Int {
        val res = intArrayOf(0, 1, 2)
        if (n <= 2) {
            return res[n]
        }
        var fibMinTwo = 1
        var fibMinOne = 2
        var fibMinN: Int = fibMinOne
        for (i in 3..n) {
            fibMinN = fibMinOne + fibMinTwo
            fibMinTwo = fibMinOne
            fibMinOne = fibMinN
        }
        return fibMinN
    }
}

fun main() {
    Solution().climbStairs(3).print()
}