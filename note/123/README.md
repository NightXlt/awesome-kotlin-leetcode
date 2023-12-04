# [Best Time to Buy and Sell Stock III][title]

## Description

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).


**Example 1:**

```
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

```

**Example 2:**

```
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.

```

**Example 3:**

```
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
```



## 思路
题意是可以进行两笔交易, 一笔交易必须在另一笔后面.
在第 i 天, 可能出现四种状态

- 未进行过任何操作；

- 只进行过一次买操作；

- 进行了一次买操作和一次卖操作，即完成了一笔交易；

- 在完成了一笔交易的前提下，进行了第二次买操作；

- 完成了全部两笔交易。

像这样有状态转移, 但不需要所有的解的情况, 第一想到动态规划方程

由于第一个状态的利润显然是 0, 可以不记录. 对于剩下的四个状态，我们分别将它们的最大利润记为 buy1, sell1, buy2 以及 sell2。

如果我们知道了第 i−1 天结束后的这四个状态， 第 i 天的状态递推为

对于 buy1 而言, 我们可以不进行操作,或者是在未操作的情况下, 购入当天股票. 取二者最小值
buy1 = min(buy1, prices[i])

对于 sell1, 我们可以不进行操作, 或者是在进行了买操作前提下进行卖出. 取个最大值
sell1 = max(sell1, prices[i] - buy1)

对于 buy2 而言, 我们可以不进行操作,或者是在第一笔交易结束后, 购入当天股票. 取二者最小值. 
怎么判断第一笔交易结束了呢? 我们在比较是引入了 prices[i] - sell1  和 当前的 buy2 进行比较. 如果前一个更小, 说明已经完成了第一笔交易


对于 sell1, 我们可以不进行操作, 或者是在进行了 buy1 操作前提下进行卖出. 取个最大值
sell2 = max(sell2, prices[i] - buy2)

共计状态方程

```text
    buy1 = min(buy1, prices[i])
    sell1 = max(sell1, prices[i] - buy1)
    buy2 = min(buy2, prices[i] - sell1)
    sell2 = max(sell2, prices[i] - buy2)
```

```kotlin

class Solution {
    fun maxProfit(prices: IntArray): Int {
        if (prices.isEmpty()) return 0
        var buy1 = prices[0]
        var buy2 = prices[0]
        var sell1 = 0
        var sell2 = 0
        for (i in 1..<prices.size) {
            buy1 = min(buy1, prices[i])
            sell1 = max(sell1, prices[i] - buy1)
            buy2 = min(buy2, prices[i] - sell1)
            sell2 = max(sell2, prices[i] - buy2)
        }
        return sell2
    }
}

```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
