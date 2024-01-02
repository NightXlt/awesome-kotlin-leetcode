# [Best Time to Buy and Sell Stock IV][title]

## Description

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



**Example 1:**

```
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
```

**Example 2:**

```
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
```


## 思路
与 123 题极其类似， 不过得用二维动规来模拟这个过程，
用 buy[i][j] 表示第 i 天进行了 j 笔交易，目前手里还有一只股票
用 sell[i][j] 表示第 i 天进行了 j 笔交易， 目前手里没有股票时的最大利润。

动规方程和 123 类似：
```text
   buy[i][j] = min(buy[i - 1][j], prices[i] - sell[i - 1][j])
   sell[i][j] = max(sell[i - 1][j], prices[i] - buy[i - 1][j - 1])
```
最终结果就是 sell 的最后一行中的最大值， 最后一天进行了 x 笔交易中最大利润

此外难想到的一个点是状态的初始化。
针对 buy[0][i] (0 天， 购入多过一个股票是不可能的哈)设置为最大值, 不难想到， 但是有可能针对 buy[0][j] 进行加减，避免越界所以需要 / 2.
而针对 sell[0][j]（0 天， 买过超过一个股票不可能的，） 设置为最小值 / 2, 表示利润最小。

还有个时间优化我想不到的是，
```text
val k = min(k, prices.size / 2)
```
因为一天买后， 隔日才可能卖，所以最大交易次数为 prices.size / 2， k（10^5） > 这个的话其实是没有意义的。 

Time complexity: O(n*k)
Space complexity: O(n*k)

```kotlin
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxProfit(k: Int, prices: IntArray): Int {
        if (prices.isEmpty()) return 0
        val k = min(k, prices.size / 2)
        val buy = Array(prices.size) { IntArray(k + 1) }
        val sell = Array(prices.size) { IntArray(k + 1) }
        buy[0][0] = prices[0]
        sell[0][0] = 0
        for (i in 1..k) {
            buy[0][i] = Int.MAX_VALUE / 2
            sell[0][i] = Int.MIN_VALUE / 2
        }
        for (i in 1..<prices.size) {
            buy[i][0] = min(buy[i - 1][0], prices[i] - sell[i - 1][0])
            for (j in 1..k) {
                buy[i][j] = min(buy[i - 1][j], prices[i] - sell[i - 1][j])
                sell[i][j] = max(sell[i - 1][j], prices[i] - buy[i - 1][j - 1])
            }
        }
        return sell.last().max()
    }
}
```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/?envType=study-plan-v2&envId=top-interview-150
[ajl]: https://github.com/Blankj/awesome-java-leetcode
