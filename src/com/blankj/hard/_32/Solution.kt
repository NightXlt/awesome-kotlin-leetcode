package com.blankj.hard._32

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun longestValidParentheses(s: String): Int {
        val dp = IntArray(s.length)
        var res = 0
        for (i in 1 until s.length) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = if (i >= 2) dp[i - 2] + 2 else 2
                //  找到我前面一个右括号的左下标吧
                } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                    val leftSideLength = if (i - dp[i - 1] >= 2) dp[i - dp[i - 1] - 2] else 0
                    dp[i] = dp[i -1] + leftSideLength + 2
                }
                res = max(res, dp[i])
            }
        }
        return res
    }
}

fun main() {
    Solution().longestValidParentheses("(()").print()
    Solution().longestValidParentheses("").print()
    Solution().longestValidParentheses( ")()())").print()
    Solution().longestValidParentheses( "()(())").print()
}