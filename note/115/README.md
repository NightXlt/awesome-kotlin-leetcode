# [Distinct Subsequences][title]

## Description

Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.


**Example:**

```
Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
```

## 思路 
每步从字符串S中取出一个字符判断它是否和字符串T中的某个字符匹配。
字符串S中的字符可能和字符串T中的多个字符匹配，如字符串T中的字符'p'可能和字符串S中的3个'p'匹配，因此每一步可能面临多个选择。
解决一个问题需要多个步骤，并且每步都可能面临多个选择，这看起来很适合运用回溯法。但由于这个问题没有要求列出字符串S中所有等于字符串T的子序列，而是只计算字符串S中等于字符串T的子序列的数目，也就是求解数目，因此，这个问题更适合运用动态规划来解决。

由于这个问题的输入有两个字符串，因此状态转移方程有两个参数。用f（i，j）表示字符串S下标从0到i的子字符串（记为S[0..i]）中等于字符串T下标从0到j的子字符串（记为T[0..j]）的子序列的数目。
如果字符串S的长度是m，字符串T的长度是n，那么f（m-1，n-1）就是字符串S中等于字符串T的子序列的数目。

如果字符串S中下标为i的字符（记为S\[i]）等于字符串T中下标为j的字符（记为T\[j]），
那么对S\[i]有两个选择：
一个是用S[i]去匹配T[j]，那么S[0..i]中等于T[0..j]的子序列的数目等于S\[0..i-1]中等于T\[0..j-1]的子序列的数目；
另一个是舍去S[i]，那么S[0..i]中等于 T[0..j] 的子序列的数目等于S[0..i-1]中等于 T[0..j] 的子序列的数目。因此，当 S[i] 等于 T[j] 时，f（i，j）等于f（i-1，j-1）+f（i-1，j）。

如果 S[i] 和 T[j] 不相同，则只能舍去 S[i]，此时 f（i，j）等于f（i-1，j）。

接着考虑字符串S和T为空的情形。由于f（0，j）表示S\[0..0]（子字符串的长度为1）中等于T\[0..j]的子序列的数目，因此f（-1，j）表示字符串S为空。
同理，f（i，-1）表示字符串T为空。当字符串S、T都为空时，两个字符串匹配，因此f（-1，-1）等于1。
如果字符串S为空而字符串T不为空，那么字符串S中不可能存在等于字符串T的子序列，即当j大于或等于0时f（-1，j）等于0。
如果字符串S不为空而字符串T为空，那么字符串S的空子序列（舍去字符串S的所有字符）等于字符串T，即当i大于或等于0时f（i，-1）等于1。

留意的是， j 访问的上界是 i + 1, t.length 的最小值。 因为当 j > i 时， s 的子序列一定不包含 t
```
class Solution {
    fun numDistinct(s: String, t: String): Int {
        if (s.length < t.length) return 0
        val dp = Array(s.length + 1) { IntArray(t.length + 1) }
        for (i in s.indices) {
            dp[i][0] = 1
        }
        for (i in t.indices) {
            dp[0][i] = 0
        }
        dp[0][0] = 1
        for (i in s.indices) {
            for (j in 0 until min(i + 1, t.length)) {
                if (s[i] == t[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1]
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1]
                }
            }
        }
        return dp[s.length][t.length]
    }

    fun numDistinctWithLessSpace(s: String, t: String): Int {
        if (s.length < t.length) return 0
        val dp = IntArray(t.length + 1)
        if (s.isNotEmpty()) {
            dp[0] = 1
        }
        for (i in s.indices) {
            for (j in min(i, t.length - 1) downTo 0) {
                if (s[i] == t[j]) {
                    dp[j + 1] += dp[j]
                }
            }
        }
        return dp[t.length]
    }

}

```
## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/distinct-subsequences/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
