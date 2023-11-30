# [Paint House][title]

## Solution
用动态规划解决问题的关键在于找出状态转移方程。根据粉刷的规则，相邻的两幢房子不能被粉刷成相同的颜色，
要计算粉刷到标号为i的房子时的成本，还需要考虑标号为i-1的房子的颜色。
3个表达式，即r（i）、g（i）、b（i），分别表示将标号为i的房子粉刷成红色、绿色和蓝色时粉刷标号从0到i的i+1幢房子的最少成本。
假设粉刷每幢房子的成本用一个二维数组costs表示，那么costs[i]中包含的3个数字分别是将标号为i的房子粉刷成红色、绿色和蓝色的成本。
当标号为i的房子被粉刷成红色时，标号为i-1的房子可以被粉刷成绿色或蓝色，因此r（i）=min（g（i-1），b（i-1））+costs[i][0]。
类似地，当标号为i的房子被粉刷成绿色时，标号为i-1的房子可以被粉刷成红色或蓝色，因此g（i）=min（r（i-1），b（i-1））+costs[i][1]；
当标号为i的房子被粉刷成蓝色时，标号为i-1的房子可以被粉刷成红色或绿色，因此b（i）=min（r（i-1），g（i-1））+costs[i][2]。

我们可以用一个完整的 3*n 二维数组来模拟这个过程。 但我们可以留意到的是， 我们关注的只是 i 和 i - 1，可以通过 3*2 的常量数组来模拟这个过程

```kotlin
import kotlin.math.min

class Solution {
    fun minCost(costs: Array<IntArray>): Int {
        if (costs.isEmpty()) return 0
        val dp = Array(3) { IntArray(2) }
        for (i in 0..2) {
            dp[i][0] = costs[0][i]
        }
        for (i in 1 until costs.size) {
            for (j in 0..2) {
                val prev1 = dp[(j + 2) % 3][(i - 1) % 2]
                val prev2 = dp[(j + 1) % 3][(i - 1) % 2]
                dp[j][i % 2] = min(prev1, prev2) + costs[i][j]
            }
        }
        val lastIndex = (costs.lastIndex) % 2
        return min(dp[0][lastIndex], min(dp[1][lastIndex], dp[2][lastIndex]))
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/paint-house/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
