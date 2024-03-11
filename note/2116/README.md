# [Check if a Parentheses String Can Be Valid][title]

## Solution
1. 字符串的个数必须是偶数个，否则返回false;
2. 从左往右遍历，左括号的数量 >= 右括号的数量(否则说明存在非法的 ')' 返回false), locked[i] = 0 的均视为左括号
3. 从右往左遍历，右括号的数量 >= 左括号的数量(否则否则说明存在非法的 '(' 返回false), locked[i] = 0 的均视为右括号


```kotlin
class Solution {
    fun canBeValid(s: String, locked: String): Boolean {
        if (s.length != locked.length || s.length % 2 == 1) return false
        var left = 0
        var right = 0
        for (i in s.indices) {
            if (s[i] == '(' || locked[i] == '0') {
                left++
            } else {
                right++
            }
            if (right > left) return false
        }
        left = 0
        right = 0
        for (i in s.indices.reversed()) {
            if (s[i] == ')' || locked[i] == '0') {
                right++
            } else {
                left++
            }
            if (left > right) return false
        }
        return true
    }
}


```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/partition-equal-subset-sum/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
