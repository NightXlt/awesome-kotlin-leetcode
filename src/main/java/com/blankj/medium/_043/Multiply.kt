package com.blankj.medium._043

import com.blankj.ext.print


class Multiply {

    fun multiply(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") return "0"
        // Mock multiply
        val res = IntArray(num1.length + num2.length)
        for (i in num1.indices.reversed()) {
            val x = num1[i] - '0'
            for (j in num2.indices.reversed()) {
                val y = num2[j] - '0'
                // Key: acc x * y to (i + j + 1) index
                res[i + j + 1] += x * y
            }
        }
        // clear up carry and then we get result
        for (i in res.lastIndex downTo 1) {
            res[i - 1] += res[i] / 10
            res[i] %= 10
        }
        // m = len(num1), n = len(num2)
        // result = num1 * num2 ; len(result) = (m + n) / (m + n - 1)
        val index = if (res[0] == 0) 1 else 0
        val sb = StringBuilder()
        for (i in index..res.lastIndex) {
            sb.append(res[i])
        }
        return sb.toString()
    }

}

fun main() {
    Multiply().multiply("123", "456").print()
    Multiply().multiply("2", "3").print()
    Multiply().multiply("2", "0").print()
}