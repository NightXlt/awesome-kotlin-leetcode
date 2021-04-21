package com.blankj.coding_interviews._64

import com.blankj.coding_interviews._004.print

class Solution {
    fun sumNums(n: Int): Int {
        var temp = n
        val flag = temp > 1 && temp.apply {
            temp += sumNums(n - 1)
        } > 0
        return temp
    }
}

fun main() {
    Solution().sumNums(3).print()
    Solution().sumNums(17).print()
    Solution().sumNums(10).print()
    Solution().sumNums(0).print()
    Solution().sumNums(997).print()
}