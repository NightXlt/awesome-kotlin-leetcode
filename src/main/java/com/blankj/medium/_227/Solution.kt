package com.blankj.medium._227

import com.blankj.ext.print
import java.util.*

class Solution {
    fun calculate(s: String?): Int {
        if (s.isNullOrBlank()) return 0
        var num = 0
        var preSign = '+'
        val digitRange = '0'..'9'
        val stack = ArrayDeque<Int>()
        for (i in s.indices) {
            // Num: acc number
            if (s[i] in digitRange) {
                num = num * 10 + (s[i] - '0')
            }
            // Symbol / last element: handle previous operation
            if (s[i] !in digitRange && s[i] != ' ' || i == s.lastIndex) {
                when (preSign) {
                    '+' -> {
                        stack.push(num)
                    }
                    '-' -> {
                        stack.push(-num)
                    }
                    '*' -> {
                        stack.push(stack.pop() * num)
                    }
                    '/' -> {
                        stack.push(stack.pop() / num)
                    }
                }
                preSign = s[i]
                num = 0
            }
        }
        return stack.fold(0) { acc, i ->
            acc + i
        }
    }
}

fun main() {
    Solution().calculate("1 + 1").print()
    Solution().calculate("2-1 + 2").print()
    Solution().calculate("3+2*2").print()
    Solution().calculate("3/2").print()
    Solution().calculate("3+5/2").print()
}