package com.blankj.medium.linkedin

import com.blankj.ext.print
import java.util.*

class Solution1 {
    fun isValidFlag(s: String?): Boolean {
        if (s.isNullOrEmpty()) return false
        val stack = ArrayDeque<Char>()
        for (i in s.indices) {
            when (val curChar = s[i]) {
                '(', '[', '{' -> stack.push(curChar)
                ')' -> {
                    if (stack.peek() != '(')
                        return false
                    stack.pop()
                }
                '}' -> {
                    if (stack.peek() != '{')
                        return false
                    stack.pop()
                }
                ']' -> {
                    if (stack.peek() != '[')
                        return false
                    stack.pop()
                }
            }
        }
        return stack.isEmpty()
    }
}

fun main() {
    /**
     * test cases
     * 1. "" false
     * 2. null false
     * 3. "({123321})" true
     * 4. "([]{)" false
     */

    Solution1().isValidFlag(null).print()
    Solution1().isValidFlag("").print()
    Solution1().isValidFlag("({123321})").print()
    Solution1().isValidFlag("([]{)").print()
}