package com.blankj.hard._772

import com.blankj.ext.print
import java.util.*

/**
 * 772. Basic Calculator III
 * 224， 227的升级版；带括号的加减乘除
 */
class Solution {

    private var index = 0

    fun calculate(s: String): Int {
        val stack: Deque<Int> = ArrayDeque()
        var num = 0
        var preSign = '+'
        while (index < s.length) {
            val c = s[index]
            val digitRange = '0'..'9'
            if (c in digitRange) {
                num = num * 10 + (c - '0')
            }
            if (c == '(') {
                index++ //index指针指到下一个字符, 递归使问题变为不带括号的简单+-*/
                num = calculate(s) //计算得到这个()的结果
            }
            //当遇到了新的运算符，就要对上一个运算符sign和累计的数字num作运算
            //空格直接无视，i继续前进
            //遇到字符串末尾，肯定是要结算的
            if (c !in digitRange && c != ' ' || index == s.lastIndex) {
                var pre: Int
                when (preSign) {
                    '+' -> stack.push(num)
                    '-' -> stack.push(-num)
                    '*' -> {
                        pre = stack.pop()
                        stack.push(pre * num)
                    }
                    '/' -> {
                        pre = stack.pop()
                        stack.push(pre / num)
                    }
                }
                preSign = c
                num = 0 //计数归位
            }
            if (c == ')') break // 开始计算局部结果，返回
            index++
        }
        return stack.sum()
    }

}

fun main() {
    Solution().calculate("-48 + 24").print()
    Solution().calculate("2147483647").print()
}