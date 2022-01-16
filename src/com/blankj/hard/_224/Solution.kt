package com.blankj.hard._224

import com.blankj.ext.print
import java.util.*

class Solution {
    fun calculate(s: String?): Int {
        if (s.isNullOrBlank()) return 0
        val stack = ArrayDeque<Int>()
        var i = 0
        var sign = 1
        var res = 0
        val digitRange = '0'..'9'
        while (i < s.length) {
            when (s[i]) {
                '+' -> {
                    sign = 1
                    i++
                }
                '-' -> {
                    sign = -1
                    i++
                }
                '(' -> {
                    stack.push(res)
                    stack.push(sign)
                    res = 0
                    sign = 1
                    i++
                }
                ')' -> {
                    res = stack.pop() * res + stack.pop()
                    i++
                }
                in digitRange -> {
                    var num = 0
                    while (i < s.length && s[i] in digitRange) {
                        num = 10 * num + (s[i] - '0')
                        i++
                    }
                    res += sign * num
                }
                else -> i++
            }
        }
        return res
    }
}

fun main() {
    Solution().calculate("1 + 1").print()
    Solution().calculate("2-1 + 2").print()
    Solution().calculate("(1+(4+5+2)-3)+(6+8)").print()
    Solution().calculate("+48 + -48").print()
    Solution().calculate("2147483647").print()
}