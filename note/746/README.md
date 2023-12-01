# [Min Cost Climbing Stairs][title]

## Solution
每走一步有若干选择， 但不要求求出所有的解，这种用 dp 解决最为方便


dp 最为关键是定义出状态转移方程， dp[i] 含义
定义 dp[i] 表示从楼梯的第 i 阶楼梯向上爬需要支付的最少费用；
因为需要考虑到 n 层阶梯， 因此我们取的 dp 长度为 length + 1, 这样不会出现漏掉数组最后一个元素的情况

确定状态转移方程
当 2≤i≤n2 时，可以从下标 i−1 使用 cost[i−1] 的花费达到下标 i，或者从下标 i−2 使用 cost[i−2] 的花费达到下标 i。
为了使总花费最小，dp[i] 应取上述两项的最小值，因此状态转移方程如下：dp[i] = min(dp[i−1]+cost[i−1], dp[i−2]+cost[i−2])

初始化状态
由于可以选择下标 0 或 1 作为初始阶梯，因此有dp[0]=dp[1]=0

遍历顺序
由状态转移方程知道 dp[i] 是从 dp[i−1] 和 dp[i−2] 转移过来所以从前往后遍历。

返回值
因为一共计算 n 阶楼梯最小花费，所以返回 dp[n]。

```kotlin
    fun minCostClimbingStairs(cost: IntArray): Int {
        if (cost.isEmpty()) error("Empty array!")
        val dp = IntArray(cost.size + 1)
        dp[0] = 0
        dp[1] = 0
        for (i in 2..cost.size) {
            dp[i] = min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1])
        }
        return dp.last()
    }
```

因为上述代码其实只依赖了 i - 1 和 i - 2. 我们可以用两个变量来模拟这个过程

```kotlin
    fun minCostClimbingStairsOptSpace(cost: IntArray): Int {
        if (cost.isEmpty()) error("Empty array!")
        var prev = 0
        var cur = 0
        for (i in 2..cost.size) {
            val next = min(prev + cost[i - 2], cur + cost[i - 1])
            prev = cur
            cur = next
        }
        return cur
    }

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/min-cost-climbing-stairs/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
