package com.blankj.coding_interviews._016

import kotlin.math.abs
import kotlin.math.pow

class Root {

    companion object {
        fun root(x: Double, n: Int): Double {
            var left = 1.0
            var right = x
            while (right - left >= 0.00001) {
                val mid = (left + right) / 2
                mid.pow(n.toDouble())
                val root = getPow(mid, n)
                if (abs(x - root) <= 0.001) {
                    return mid
                }
                if (root - x > 0.001) {
                    right = mid
                } else {
                    left = mid
                }
            }
            return -1.0
        }

        private fun getPow(x: Double, n: Int): Double {
            var res = 1.0
            var exponent = n
            var base = x
            while (exponent != 0) {
                if (exponent % 2 != 0) {
                    res *= base
                }
                exponent /= 2
                base *= base
            }
            return res
        }
    }

}
fun main() {
    println(Root.root(160.0, 3))
    println(Root.root(7.0, 3))
    println(Root.root(9.0, 2))
}