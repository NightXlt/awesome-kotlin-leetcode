# [Length of Longest Fibonacci Subsequence][title]

## Solution
在数组A=[1，2，3，4，5，6，7，8]中，A[7]等于8。
数字8既可以在1、2、3、5（结尾数字为A[4]）的基础上形成更长的斐波那契数列1、2、3、5、8，也可以和数字6（A[5]）一起形成斐波那契数列2、6、8，
还可以和数字7（A[6]）一起组成斐波那契数列1、7、8。虽然序列2、6和1、7本身都不是斐波那契数列，但在后面添加数字8之后就变成斐波那契数列。
由于以A[i]为结尾的斐波那契数列的长度依赖于它前一个数字A[j]，不同的A[j]能和A[i]形成不同的斐波那契数列，它们的长度也可能不同。因此，状态转移方程有两个参数i和j，f（i，j）表示以A[i]为最后一个数字、A[j]为倒数第2个数字的斐波那契数列的长度。如果数组中存在一个数字k，使A[i]=A[j]+A[k]（0≤k＜j＜i），那么f（i，j）=f（j，k）+1，即在以A[j]为最后一个数字、A[k]为倒数第2个数字的斐波那契数列的基础上增加一个数字A[i]，形成更长的一个数列。
f（i，j）的值可能是2，此时虽然A[i]和A[j]这两个数字现在还不能形成一个有效的斐波那契数列，但可能会在之后增加一个新的数字使之形成长度为3甚至更长的斐波那契数列。

```kotlin
 class Solution {
     fun lenLongestFibSubseq(arr: IntArray): Int {
         if (arr.isEmpty()) return 0
         val dp = Array(arr.size) { IntArray(arr.size) }
         var res = 2
         val map = arr.mapIndexed { index, i -> i to index }
             .associate { it }
             .toMutableMap()
         for (i in 1 until arr.size) {
             for (j in 0 until i) {
                 val k = map.getOrDefault(arr[i] - arr[j], -1)
                 dp[i][j] = if (k in 0 until j) dp[j][k] + 1 else 2
                 res = max(res, dp[i][j])
             }
         }
         return if (res > 2) res else 0
     }
 }


```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/koko-eating-bananas/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
