# [Coin Change][title]

## Solution
背包问题(动态规划的一种)
因为每个物品可以无限取, 是完全背包问题.
完全背包外层遍历 num, 内层正序遍历 target.

我记得很多年前 LCY 老师讲过这类问题，但记不太清当时咋解的，无脑贪心会有问题。
比如当优先取了大额零钱若干张后会导致后续取小额零钱可能凑不到目标值。但如果由若干张小额零钱拼凑是可以组成目标值的。
参考：https://leetcode-cn.com/problems/coin-change/solution/yi-pian-wen-zhang-chi-tou-bei-bao-wen-ti-sq9n/
这个题解很详细，介绍了各种背包的原理模板套路。

假如我知道了 前面 i  - coin 钱的兑换方式， 有一枚硬币 coin。 i + coin 后 小于 amount，那么 当前的钱 i 的兑换方式为
min(当前i 的兑换零钱数目， (i - coin)的兑换零钱数目 + 1)
举个栗子
我有两块钱，零钱只有一块，那么兑换数目是 2.
可当零钱有1, 2块时，兑换数目就是 min(2， 1)

动规方程
```
 dp[i] = min(dp[i], dp[i-coin]+1)
```
dp\[i\]：表示 i 块钱的最小兑换数目

时间复杂度：O(nt) // t 为 硬币数目, n 为 coins 长度
空间复杂度：O(t)

值得留意的是这里有个处理小细节, 数组初始化为一个较大值, 用来防止影响 min 的比较.
但不能取 Int.Max_VALUE 哈, 取了迭代时 +1 的话, 会出现越界.

```kotlin
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0
        for (coin in coins) {
            for (i in coin..amount) {
                dp[i] = min(dp[i], dp[i - coin] + 1)
            }
        }
        return if (dp[amount] > amount) -1 else dp[amount]
    }
```


## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/coin-change/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
