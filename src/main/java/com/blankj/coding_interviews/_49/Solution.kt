package com.blankj.coding_interviews._49

import com.blankj.ext.print
import kotlin.math.min


class Solution {
    fun nthUglyNumber(n: Int): Int {
        if (n <= 0) return 0
        val result = IntArray(n)
        result[0] = 1
        var nextUglyNumIndex = 1
        var pMultiply2 = 0
        var pMultiply3 = 0
        var pMultiply5 = 0
        while (nextUglyNumIndex < n) {
            val nextUglyNum = minOf(result[pMultiply2] * 2, result[pMultiply3] * 3, result[pMultiply5] * 5) // 求出下一个丑数中最小的一个，保证比当前丑数更小的丑数已经在数组中了
            result[nextUglyNumIndex] = nextUglyNum
            if (result[pMultiply2] * 2 <= nextUglyNum) {
                pMultiply2++
            }
            if (result[pMultiply3] * 3 <= nextUglyNum) {
                pMultiply3++
            }
            if (result[pMultiply5] * 5 <= nextUglyNum) {
                pMultiply5++
            }
            nextUglyNumIndex++
        }
        return result[n - 1]
    }

}

fun main() {
    Solution().nthUglyNumber(2).print()
    Solution().nthUglyNumber(10).print()
}
