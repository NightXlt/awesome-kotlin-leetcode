package com.blankj.coding_interviews._66

import com.blankj.coding_interviews._004.print

class Solution {
    fun constructArr(a: IntArray?): IntArray {
        if (a == null || a.isEmpty()) return intArrayOf()
        var result = IntArray(a.size)
        var leftMultiplyResult = 1
        for (i in a.indices) {
            result[i] = leftMultiplyResult
            leftMultiplyResult *= a[i]
        }
        var rightMultiplyResult = 1
        for (i in a.indices.reversed()) {
            result[i] *= rightMultiplyResult
            rightMultiplyResult *= a[i]
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