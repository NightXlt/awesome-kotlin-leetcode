# [Regular Expression Matching][title]

## Description

Given an input string (`s`) and a pattern (`p`), implement regular expression matching with support for `'.'` and `'*'`.

```
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
```

The matching should cover the **entire** input string (not partial).

**Note:**

- `s` could be empty and contains only lowercase letters `a-z`.
- `p` could be empty and contains only lowercase letters `a-z`, and characters like `.` or `*`.

**Example 1:**

```
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**

```
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```

**Example 3:**

```
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```

**Example 4:**

```
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
```

**Example 5:**

```
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
```

**Tags:** String, Dynamic Programming, Backtracking


## 思路 0

题意是让让你从判断 `s` 字符串是否正则匹配于 `p`，这道题和 [Wildcard Matching][044] 很是相似，区别在于 `*`，通配符的 `*` 是可以随意出现的，跟前面字符没有任何关系，其作用是可以表示任意字符串；而正则匹配的 `*` 不能单独存在，前面必须具有一个字符，其意义是表明前面的这个字符个数可以是任意个数，包括 0 个。首先我们用递归的方式来实现，其思路如下：

* 如果 `s` 和 `p` 都为空，那么返回 `true`；

* 如果 `p` 的长度为 1，当 `s` 的长度也为 1，并且他们首位匹配则返回 `true`，否则返回 `false`；

* 如果 `p` 的第二个字符不为 '*'，如果 `s` 为空，那就返回 `false`，首位匹配则返回递归调用他们去掉首位的子字符串，否则返回 `false`；

* 如果 `p` 的第二个字符为 '*'，循环当 `s` 不为空，且首位匹配，如果递归调用是否匹配 `s` 字符串和 `p` 去掉前两位的子字符串，则返回 `true`，否则 `s` 去掉首字母继续循环；

* 返回递归调用 `s` 字符串和 `p` 去掉前两位的子字符串是否匹配。

```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() == 1) {
            return s.length() == 1 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        }
        if (p.charAt(1) != '*') {
            if (s.isEmpty()) return false;
            return (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                    && isMatch(s.substring(1), p.substring(1));
        }
        // match 1 or more preceding element
        while (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) return true;
            s = s.substring(1);
        }
        // match 0 preceding element
        return isMatch(s, p.substring(2));
    }
}
```


## 思路 1

我们可以把上面的思路更简单化，如下：

* 如果 `s` 和 `p` 都为空，那么返回 `true`；

* 如果 `p` 的第二个字符为 `*`，由于 `*` 前面的字符个数可以为任意，那么我们先递归调用个数为 0 的情况；或者当 `s` 不为空，如果他们的首字母匹配，那么我们就递归调用去掉去掉首字母的 `s` 和完整的 `p`；

* 如果 `p` 的第二个字符不为 `*`，那么我们就老老实实判断第一个字符是否匹配并且递归调用他们去掉首位的子字符串。

```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2))
                    || (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                    && isMatch(s.substring(1), p));
        }
        return !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                && isMatch(s.substring(1), p.substring(1));
    }
}
```

## 思路 2

另一种思路就是动态规划了，我们定义 `dp[i][j]` 的真假来表示 `s[0..i)` 是否匹配 `p[0..j)`，通过思路 1，我们可以确定其状态转移方程如下所示：
其中 dp[i][j - 2] 表示 * 不包含前一个元素的情况， 后面的或是包含的情况，
(p[j - 2] == s[i - 1] || p[j - 2] == '.') && dp[i - 1][j]

* 如果 `p[j - 1] == '*'`, `dp[i][j] = dp[i][j - 2] || (p[j - 2] == s[i - 1] || p[j - 2] == '.') && dp[i - 1][j];`；

* 如果 `p[j - 1] != '*'`，`dp[i][j] = dp[i - 1][j - 1] && (p[j - 1] == '.' || p[j - 1] == s[i - 1]);`。


补充下柯基这个思路我之前没想到的一些点，之前自己写的是逆序版本， 没有深度思考为啥要逆序，正序也是可以求解的
这个版本是顺序的。 上面的动规方程， 柯基讲的很明白，
可以看到这里顺序处理起来会比较麻烦， 考虑的情况也比较多, 需要单独初始化二维 dp 第一行 s 为空串的情况， p 存在 '*' 的情况。

```kotlin
class RegexMatchWithSerialOrder {
    fun isMatch(s: String, p: String): Boolean {
        // length + 1： match empty string
        val m = s.length + 1
        val n = p.length + 1
        val dp = Array(m) { Array(n) { false } }
        dp[0][0] = true
        for (i in 2..<n step 2) {
            dp[0][i] = dp[0][i - 2] && p[i - 1] == '*'
        }
        for (i in 1..<m) {
            for (j in 1..<n) {
                dp[i][j] = if (p[j - 1] == '*') {
                    dp[i][j - 2] || (dp[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'))
                } else {
                    dp[i - 1][j - 1] && (s[i - 1] == p [j - 1] || p[j - 1] == '.')
                }
            }
        }
        return dp.last().last()
    }

}

```

## 思路3
逆序的 dp. dp[i][j] 数组的含义是 (i..<s.length)(j..<p.length) 是否相等
那么 最后一行和最后一列分别表示 s 空串和 p 空串。
状态方程为：
* `curMatch = i < s.length && (s[i] == p[j] || p[j] == '.')`
* 如果 `p[j + 1] == '*'`, ` dp[i][j] = dp[i][j + 2] || curMatch && dp[i + 1][j]  (j < p.length - 1)`； 

* 如果 `p[j - 1] != '*'`，`  dp[i][j] = curMatch && dp[i + 1][j + 1].`。

其中 * 的情况有点复杂， 自己已经记不清含义了， 前面一般是 * 不包含的情况， 比较好理解,
* 包含的情况的话， 除了判断当前 s[i] 和 p[j] 的匹配情况， 还要看 (i + 1..s.length) 与 (jj..p.length) 的匹配情况， 因为，因为，因为 j 在这开始重复了
后面的 dp[i + 1][j] 需要时间领会， 而且根据以前自己的思考看， 这里的时序是不能调整的， || 前面隐含贪心思想。
正序对我而言，最难的思考出状态转移方程，因为里面的 dp[i][j] 对应的是 （0..i-1) 和 （0.. j-1）转换起来比较头疼。

```kotlin

class RegexMatch {

    fun isMatch(s: String, p: String): Boolean {
        // length + 1： match empty string
        val dp = Array(s.length + 1) { Array(p.length + 1) { false } }
        // "" == ""
        dp[s.length][p.length] = true
        // i from s.length is for empty string match p
        for (i in dp.indices.reversed()) {
            for (j in p.indices.reversed()) {
                //  i < s.length: prevent OOB
                val curMatch = i < s.length && (s[i] == p[j] || p[j] == '.')
                // j + 1 < p.length: prevent OOB
                dp[i][j] = if (j + 1 < p.length && p[j + 1] == '*') {
                    dp[i][j + 2] || (curMatch && dp[i + 1][j])
                } else {
                    curMatch && dp[i + 1][j + 1]
                }
            }
        }
        return dp[0][0]
    }
}
```

## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[044]: https://github.com/Blankj/awesome-java-leetcode/blob/master/note/044/README.md
[title]: https://leetcode.com/problems/regular-expression-matching
[ajl]: https://github.com/Blankj/awesome-java-leetcode
