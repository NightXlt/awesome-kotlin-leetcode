package com.blankj.coding_interviews._010

import com.blankj.ext.print

class Solution {
    fun fib(n: Int): Int {
        val fib = intArrayOf(0, 1)
        if (n in 0..1) return fib[n]
        var fibMinusOne = 1
        var fibMinusTwo = 0
        var fibN = fibMinusTwo
        for (i in 2..n) {
            fibN = (fibMinusOne + fibMinusTwo) % 1000000007
            fibMinusTwo = fibMinusOne
            fibMinusOne = fibN
        }
        return fibN
    }
}

fun main() {
    Solution().fib(2).print() // boundary case
    Solution().fib(5).print()
    Solution().fib(100).print() // big number
}
