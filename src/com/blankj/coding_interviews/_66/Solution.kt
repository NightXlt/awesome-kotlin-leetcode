package com.blankj.coding_interviews._66

import com.blankj.coding_interviews._004.print

class Solution {
    fun constructArr(a: IntArray?): IntArray {
        if (a == null || a.isEmpty()) return intArrayOf()
        var result = IntArray(a.size)
        result[0] = 1
        for (i in 1..a.lastIndex) {
            result[i] = result[i - 1] * a[i - 1]
        }
        var temp = 1
        for (i in a.indices.reversed()) {
            result[i] *= temp
            temp *= a[i]
        }
        return result
    }
}

fun main() {
    Solution().constructArr(null).print()
    Solution().constructArr(intArrayOf()).print()
    Solution().constructArr(intArrayOf(1, 2, 3, 4, 5)).print()
    Solution().constructArr(intArrayOf(3, 3, 3)).print()
}