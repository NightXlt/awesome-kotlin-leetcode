package com.blankj.easy._020

import com.blankj.ext.print

class Solution {
    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        val map = mapOf(
            ')' to '(',
            ']' to '[',
            '}' to '{',
        )
        val values = map.values
        for (c in s) {
            when (c) {
                in values -> stack.add(c)
                in map.keys-> {
                    if (stack.isEmpty()) {
                        return false
                    }
                    val topElement = stack.removeLast()
                    if (topElement != map[c]) {
                        return false
                    }
                }
            }
        }
        return stack.isEmpty()
    }
}

fun main() {
    Solution().isValid("()").print()
    Solution().isValid("()[]{}").print()
    Solution().isValid("(]").print()
}