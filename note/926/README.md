# [Flip String to Monotone Increasing][title]

## Solution
应用动态规划解决问题总是从分析状态转移方程开始的。
如果一个只包含'0'和'1'的字符串S的长度为i+1，它的字符的下标范围为0～i。
在翻转下标为i的字符时假设它的前i个字符都已经按照规则翻转完毕，所有的字符'0'都位于'1'的前面。
由于翻转下标为i的字符依赖于前i个字符翻转之后最后一个字符是'0'还是'1'，因此要分为两种情况讨论。

假设函数 f（i）表示把字符串中从下标为0的字符到下标为i的字符（记为 S[0..i]，字符串中前i+1个字符组成的子字符串）变成符合要求的字符串并且最后一个字符是'0'所需要的最少翻转次数。

假设函数 g（i）表示把字符串中 S[0..i] 变成符合要求的字符串并且最后一个字符是 '1' 所需要的最少翻转次数。

如果字符串的长度是n，那么 f（n-1） 和 g（n-1）就是翻转整个字符串使字符串符合要求并且最后一个字符分别变成'0'和'1'的最少翻转次数， 它们的最小值就是整个问题的解。


如果翻转之后下标为i的字符是'0'，那么下标为i-1的字符一定是'0'，否则就不满足所有的字符'0'位于'1'的前面的这个要求。当输入字符串中下标为i的字符（即 S[i]）是 '0' 时，这一步不需要翻转，
f（i）=f（i-1）；当输入字符串中下标为i的字符是'1'时，f（i）=f（i-1）+1，因为要把下标为i的字符翻转成'0'。

如果翻转之后下标为i的字符是 '1'，那么无论下标为i-1的字符是'0'还是'1'都满足题目的要求。当输入字符串S[i]是'0'时，g（i）=min[f（i-1）， g（i-1）] + 1，因为要把第 i 个字符翻转成 '1'；当S[i]是'1'时，此时不需要翻转字符，因此 g（i） = min[f（i-1），g（i-1）]。

由于计算 f（i）和 g（i）只需要用到 f（i-1）和 g（i-1） 的值，因此并不需要用一个长度为 n 的数组来保存 f（i） 和 g（i）。

dp 中每行的长度为 2， f（i） 和 g（i） 的值保存在对应行 “i%2” 的位置。

```kotlin
import kotlin.math.min
fun minFlipsMonoIncr(s: String): Int {
    if (s.isEmpty()) return 0
    val dp = Array(2) { IntArray(2) }
    dp[0][0] = if (s[0] == '0') 0 else 1
    dp[1][0] = if (s[0] == '1') 0 else 1

    for (i in 1 until s.length) {
        val prev0 = dp[0][(i - 1) % 2]
        val prev1 = dp[1][(i - 1) % 2]
        dp[0][i % 2] = prev0 + if (s[i] == '1') 1 else 0
        dp[1][i % 2] = min(prev1, prev0) + if (s[i] == '0') 1 else 0
    }
    return min(dp[0][s.lastIndex % 2], dp[1][s.lastIndex % 2])
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/flip-string-to-monotone-increasing/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
