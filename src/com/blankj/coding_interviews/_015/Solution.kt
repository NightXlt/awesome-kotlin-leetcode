package com.blankj.coding_interviews._015

import com.blankj.coding_interviews._004.print

class Solution {
    // you need to treat n as an unsigned value
    fun hammingWeight(n: Int): Int {
        var n = n
        var count = 0
        while (n != 0) {
            n = n and n - 1
            count++
        }
        return count
    }
}
fun main() {
    // 负数的最大值和最小值不能直接使用0xFFFFFFFF，需要加上 toInt
    Solution().hammingWeight(1).print() // pos min boundary
    Solution().hammingWeight(0X7FFFFFFF).print() // pos max boundary
    Solution().hammingWeight(0xFFFFFFFF.toInt()).print() // neg min
    Solution().hammingWeight(0x80000000.toInt()).print() // neg max
    Solution().hammingWeight(0).print() //zero
    Solution().hammingWeight(5).print() // normal case
}
