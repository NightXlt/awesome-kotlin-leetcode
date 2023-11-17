package com.blankj.coding_interviews._62

import com.blankj.ext.print

class Solution {
    fun lastRemaining(n: Int, m: Int): Int {
        if (n < 1 || m < 1) return -1
        var result = 0
        for (i in 2..n) {
            result = (result + m) % i
        }
        return result
    }

}

fun main() {
    Solution().lastRemaining(5, 3).print()
    Solution().lastRemaining(10, 17).print()
    Solution().lastRemaining(10, 10).print()
    Solution().lastRemaining(0, 0).print()
    Solution().lastRemaining(4000, 997).print()
}