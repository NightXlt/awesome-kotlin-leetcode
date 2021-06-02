# [edit-distance][title]

## Solution
这道题类似正则匹配的方式，纵向表示匹配串，横向表示模式串。
逐个进行匹配
>问题1：如果 word1[0..i-1] 到 word2[0..j-1] 的变换需要消耗 k 步，那 word1[0..i] 到 word2[0..j] 的变换需要几步呢？
 
>答：先使用 k 步，把 word1[0..i-1] 变换到 word2[0..j-1]，消耗 k 步。再把 word1[i] 改成 word2[j]，就行了。如果 word1[i] == word2[j]，什么也不用做，一共消耗 k 步，否则需要修改，一共消耗 k + 1 步。
 
>问题2：如果 word1[0..i-1] 到 word2[0..j] 的变换需要消耗 k 步，那 word1[0..i] 到 word2[0..j] 的变换需要消耗几步呢？
 
>答：先经过 k 步，把 word1[0..i-1] 变换到 word2[0..j]，消耗掉 k 步，再把 word1[i] 删除，这样，word1[0..i] 就完全变成了 word2[0..j] 了。一共 k + 1 步。
 
>问题3：如果 word1[0..i] 到 word2[0..j-1] 的变换需要消耗 k 步，那 word1[0..i] 到 word2[0..j] 的变换需要消耗几步呢？
 
>答：先经过 k 步，把 word1[0..i] 变换成 word2[0..j-1]，消耗掉 k 步，接下来，再插入一个字符 word2[j], word1[0..i] 就完全变成了 word2[0..j] 了。
 
 从上面三个问题来看，word1[0..i] 变换成 word2[0..j] 主要有三种手段，用哪个距离最小用哪个
 像这样涉及到前序状态，能想到啥呢？ 动规，假如我知道了前序的 xxx， 那么后续的 yyy 就可以得到了。这种是动规的套路了。
 动规想清楚的，
 - 动规方程
 - 起始状态
 - 终止的返回值
 
 正则匹配，通配符，编辑距离这三道题是同一种类型，要留意啊。
 
 动规方程
```
i: 1 ~ word1.length + 1 j: 1 ~ word2.length + 1
                dp[i-1][j - 1]                                             word1[i - 1] = word2[j - 1] 
dp[i][j] =   {
                1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])      word1[i - 1] != word2[j - 1]   
```
dp\[i\]\[j\]：表示 word1\[0..i - 1\] 匹配 word2\[0..j-1\] 变换所需距离。

起始状态： dp\[i\]\[0\], dp\[0\]\[j\] 会被初始化为i，j. 表示空串的情况下所需的编辑距离

终止的返回值：dp\[word1.length\]\[word2.length\]表示 word1和 word2 变换所需距离

时间复杂度：O(mn) m: word1 的长度   n: word2 的长度
空间复杂度：O(mn)

```kotlin
class Solution {
    fun minDistance(word1: String, word2: String): Int {
        val length1 = word1.length + 1
        val length2 = word2.length + 1
        val dp = Array(length1) {
            IntArray(length2)
        }
        repeat(length1) { i -> dp[i][0] = i }
        repeat(length2) { j -> dp[0][j] = j }
        for (i in 1 until length1) {
            for (j in 1 until length2) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                }
            }
        }
        return dp[word1.length][word2.length]
    }

    private fun min(a: Int, b: Int, c: Int): Int = min(min(a, b), c)
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/edit-distance/submissions/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
