package com.blankj.easy._338

class Solution {
    fun countBits(num: Int): IntArray {
        val res = IntArray(num + 1)
        for (i in 1..num) {
            res[i] = res[i and (i - 1)] + 1
        }
        return res
    }
}