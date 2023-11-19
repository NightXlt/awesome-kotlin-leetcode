package com.blankj.medium._150

import com.blankj.ext.print

class Solution {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = ArrayDeque<Int>()
        for (token in tokens) {
            when (token) {
                "+", "-", "*", "/" -> {
                    val num2 = stack.removeLast()
                    val num1 = stack.removeLast()
                    stack.add(
                        calculate(
                            num1,
                            num2,
                            token
                        )
                    )
                }
                else -> {
                    stack.add(token.toInt())
                }
            }
        }
        return stack.last()
    }

    private fun calculate(num1: Int, num2: Int, token: String): Int {
        return when (token) {
            "*" -> num1 * num2
            "+" -> num1 + num2
            "-" -> num1 - num2
            "/" -> num1 / num2
            else -> error("Illegal operator: $token")
        }
    }
}

fun main() {
    Solution().evalRPN(
        arrayOf("2","1","+","3","*")
    ).print()
    Solution().evalRPN(
        arrayOf("4","13","5","/","+")
    ).print()
}