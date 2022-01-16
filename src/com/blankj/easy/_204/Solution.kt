package com.blankj.easy._204

import com.blankj.ext.print

class Solution {
    fun countPrimes(n: Int): Int {
        val isPrime = BooleanArray(n) { true }
        var res = 0
        for (i in 2 until n) {
            if (isPrime[i]) {
                res += 1
                var j = i.toLong() * i
                while (j < n) {
                    isPrime[j.toInt()] = false
                    j += i
                }
            }
        }
        return res
    }
}

fun main() {
    Solution().countPrimes(2).print()
    Solution().countPrimes(10).print()
    Solution().countPrimes(1).print()
}