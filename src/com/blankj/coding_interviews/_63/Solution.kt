package com.blankj.coding_interviews._63

import com.blankj.coding_interviews._004.print
import java.io.File

class Solution {
    fun add(a: Int, b: Int): Int {
        var sum: Int
        var carry: Int
        var a = a
        var b = b
        do {
            sum = a xor b
            carry = (a and b) shl 1
            a = sum
            b = carry
        } while (b != 0)
        return sum
    }

}

fun main() {
    Solution().add(3, 0).print()
    Solution().add(5, 3).print()
    Solution().add(10, 17).print()
    Solution().add(10, 10).print()
    Solution().add(0, 0).print()
    Solution().add(4000, 997).print()
}