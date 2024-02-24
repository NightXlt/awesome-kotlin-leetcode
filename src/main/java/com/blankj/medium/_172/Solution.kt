package com.blankj.medium._172

import com.blankj.ext.print

class Solution {
    fun trailingZeroes(n: Int): Int {
        var res = 0
        var temp = n
        while (temp != 0) {
            temp /= 5
            res += temp
        }
        return res
    }
}

fun main() {
    Solution().trailingZeroes(14).print()
    Solution().trailingZeroes(20).print()
    Solution().trailingZeroes(24).print()
}