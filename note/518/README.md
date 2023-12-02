# [Coin Change II][title]

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

```text
Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1

```

## Solution
背包问题, 因为每个物品可以无限取, 是完全背包问题.
完全背包外层遍历 num, 内层正序遍历 target.

定义 dp[i] = i 对应的组合总数.
dp[i - coin] 那么加上当前 coin 就可以构成 i 了.
i 的集合总数 = 之前的 i 的集合总数 + i - coin 的集合总数
dp[i] = dp[i] + dp[i - coin]

```kotlin
class Solution {
    fun change(amount: Int, coins: IntArray): Int {
        if (coins.isEmpty()) return 0
        val dp = IntArray(amount + 1)
        dp[0] = 1
        for (coin in coins) {
            for (i in coin..amount) {
                dp[i] += dp[i - coin]
            }
        }
        return dp[amount]
    }
}


```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/coin-change-ii/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
