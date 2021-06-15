package com.blankj.coding_interviews._016

import com.blankj.coding_interviews._004.print
import java.lang.IllegalArgumentException
import kotlin.math.abs

class Solution {


    fun myPow(x: Double, n: Int): Double {
        var res = 1.0
        var i = n
        var x = x
        // double cant use == op comparison simply
        if (abs(x) < 0.00000001 && n < 0) throw IllegalArgumentException("base is 0, but exponent is less than 0")
        while (i != 0) {
            if (i % 2 != 0) {
                res *= x
            }
            i /= 2
            x *= x
        }
        return if (n < 0) 1.0 / res else res
    }

    fun myPow1(x: Double, n: Int): Double {
        if (n == 0) return 1.0
        // double cant use == op comparison simply
        if (abs(x) < 0.00000001 && n < 0) throw IllegalArgumentException("base is 0, but exponent is less than 0")
        //如果n小于0，把它改为正数，并且把1/x提取出来一个,避免出现-Int.MIN_VALUE 越界
        if (n < 0) return 1 / x * myPow(1 / x, -n - 1)
        return if (n % 2 == 0) myPow(x * x, n / 2) else x * myPow(x * x, n / 2)
    }

}


fun main() {
    Solution().myPow(0.0, -1).print()  // base 0, exp -1
    Solution().myPow(0.000001, -1).print()  // base 0, exp -1
    Solution().myPow(0.0, 0).print()  // base 0, exp 0
    Solution().myPow(2.0, 0).print() // base pos, exp 0
    Solution().myPow(-1.0, 0).print()// base neg, exp 0
    Solution().myPow(2.0, 1).print() // base pos, exp pos
    Solution().myPow(2.0, -2).print() // base pos, exp pos
    Solution().myPow(-2.0, 2).print() // base neg, exp pos
    Solution().myPow(-2.0, -2).print()// base neg, exp neg
    Solution().myPow(2.10000, 3).print() // fraction
    Solution().myPow(2.0, Int.MAX_VALUE).print() // boundary case
    Solution().myPow(2.0, Int.MIN_VALUE).print() // implicit and dangerous case
}
