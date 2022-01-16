package com.blankj.coding_interviews._010

import com.blankj.ext.print

class Solution {
    fun fib(n: Int): Int {
        val fib = intArrayOf(0, 1)
        if (n in 0..1) return fib[n]
        var fibOne = 1
        var fibTwo = 0
        var fibN = fibTwo
        for (i in 2..n) {
            fibN = (fibOne + fibTwo) % 1000000007
            fibTwo = fibOne
            fibOne = fibN
        }
        return fibN
    }
}

fun main() {
    Solution().fib(2).print() // boundary case
    Solution().fib(5).print()
    Solution().fib(100).print() // big number
}
