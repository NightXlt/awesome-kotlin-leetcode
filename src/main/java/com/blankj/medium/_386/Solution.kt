package com.blankj.medium._386

import com.blankj.ext.print

class Solution {
    fun lexicalOrder(n: Int): List<Int> {
        var j = 1
        val res = mutableListOf<Int>()
        for (i in 0..<n) {
            res.add(j)
            if (j * 10 <= n) {
                j *= 10
            } else {
                // go back one layer
                while (j % 10 == 9 || j + 1 > n) j /= 10
                // go right
                j++
            }
        }
        return res
    }
}

fun main() {
Solution().lexicalOrder(13).print()
}