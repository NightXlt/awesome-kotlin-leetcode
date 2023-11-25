package com.blankj.easy._067

import com.blankj.ext.print

class MySqrt {
    fun mySqrt(x: Int): Int {
        var left = 1
        var right = x
        while (left <= right) {
            var mid = (left + right) shr 1
            if (mid <= x / mid) {
                if (mid + 1 > x / (mid + 1)) {
                    return mid
                }
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return 0
    }
}

fun main() {
    MySqrt().mySqrt(8).print()
    MySqrt().mySqrt(4).print()
    MySqrt().mySqrt(2).print()
}