package com.blankj.medium._67

class Solution {
    fun addBinary(a: String, b: String): String {
        val res = StringBuilder()
        var (i, j) = a.length - 1 to b.length - 1
        var carry = 0
        while (i > 0 || j > 0) {
            val digitA = if (i >= 0) a[i--] - '0' else 0
            val digitB = if (j >= 0) b[j--] - '0' else 0
            var sum = digitA + digitB + carry
            carry = if (sum >= 2) 1 else 0
            sum = if (sum >= 2) sum - 2 else sum
            res.append(sum)
        }
        if (carry == 1) {
            res.append(1)
        }
        return res.reverse().toString()
    }
}

fun main() {
    Solution().addBinary("1010", "1011")
}