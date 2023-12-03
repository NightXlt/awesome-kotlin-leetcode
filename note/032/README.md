# [Longest Valid Parentheses][title]

Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses
substring
.

```text

Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0
```


## 思路
每一步 ')' 的最长有效括号长度取决于与前一个的字符.
如果是 '(' 就刚好构成 “()”. 
如果是 “)” 构成 “))”, 如果 i - 前面的字符的最长有效括号长度 - 1 是 “(”, 当前字符的最长有效长度就等于前面的 ')' 的 最长有效长度 + 当前括号匹配最左边的 '(' 前面一个字符的最长有效括号长度. 

```kotlin
class Solution {
    fun longestValidParentheses(s: String): Int {
        val dp = IntArray(s.length)
        var res = 0
        for (i in 1 until s.length) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    //
                    dp[i] = if (i >= 2) dp[i - 2] + 2 else 2
                    //  找到我前面一个右括号的左下标吧
                } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                    val leftSideLength = if (i - dp[i - 1] >= 2) dp[i - dp[i - 1] - 2] else 0
                    // 记得加上自己
                    dp[i] = dp[i -1] + leftSideLength + 2
                }
                res = max(res, dp[i])
            }
        }
        return res
    }
}

```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/longest-valid-parentheses/description/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
