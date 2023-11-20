package com.blankj.easy._868

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun binaryGap(n: Int): Int {
        if (n == 0) return 0
        var temp = n
        var i = 0
        var last = -1
        var res = 0
        while (temp != 0) {
            if (temp and 1 == 1) {
                if (last != -1) {
                    res = max(res, i - last - 1)
                }
                last = i
            }
            temp = temp shr 1
            i++
        }
        return res
    }
}

fun main() {
    Solution().binaryGap(22).print()
    Solution().binaryGap(8).print()
    Solution().binaryGap(5).print()
    Solution().binaryGap(1041).print()
    Integer.toBinaryString(1041).print()
}