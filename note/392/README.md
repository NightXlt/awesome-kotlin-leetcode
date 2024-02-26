# [Is Subsequence][title]

## Solution

记录下 DP 的解法，dp[i][j] 表示的是字符串 t 的下标 i 后面第一个字符 j 所在的位置, 这个有点拗口。

```text
exp:
t: "abc"
t[0][2] = 2  表示的是 a 后面第一个字符 c 所在的位置是 2
```
当 t[i] == j 时， 那么 dp[i][j] = i
当 t[i] != j 时， 那么 dp[i][j] = dp[i + 1][j]

此外就是边界情况， 当 i 为最后一个字符时， dp[i + 1][j] = t.length 表示为不匹配。
也因此 dp 的 二维 size 为 (t.length + 1) * 26
因为 dp 的状态方程是取决于后面一个字符， 故我们需要倒着进行 t 的遍历。
但状态存在 dp 中后， 后续 Followup 就会更加高效。

```kotlin
class Solution {

    fun isSubsequenceFollowUp(s: String, t: String): Boolean {
        val m = t.length
        val dp = Array(m + 1) { IntArray(26) }
        for (i in 0..<26) {
            dp[m][i] = m
        }
        for (i in t.indices.reversed()) {
            for (j in 0..<26) {
                if (t[i] == 'a' + j) {
                    dp[i][j] = i
                } else {
                    dp[i][j] = dp[i + 1][j]
                }
            }
        }
        var indexOfT = 0
        // 针对每个 s 的字符尝试找到在 t 的位置，如果都能找到说明是子串，否则不是。
        for (i in s.indices) {
            if (dp[indexOfT][s[i] - 'a'] == m) {
                return false
            }
            indexOfT = dp[indexOfT][s[i] - 'a'] + 1
        }
        return true
    }
    
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true
        if (s.length > t.length) return false
        var i = 0
        var j = 0
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                i++
            }
            j++
        }
        return i == s.length
    }

}


```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/is-subsequence/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
