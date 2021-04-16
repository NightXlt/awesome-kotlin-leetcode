#[n个骰子的点数][title]

## Solution
核心思想是动规。
f(x) = f(x - 1) * 1/6 + f(x - 2) * 1/6 + f(x - 3) * 1/6 + f(x - 4) * 1/6 + f(x - 5) * 1/6 + f(x - 6) * 1/6 x>=6 

f(x) 表示当前点数和为 x的概率， 其等于x-1..x-6与 1/6的累积和。
两个骰子和为 4 的概率 == 第一个骰子为 1 的概率 * 1 / 6 + 第一个骰子为 2 的概率 * 1 / 6 + 第一个骰子为 3 的概率 * 1 / 6 === 3 / 36，
但不能直接套用上面的公式，为什么呢， 比如哈 f(3) = f(2) + f(1) + f(0) + f(-1) + f(-2). 小于 0 后的和就已经是非法的和。为了避免这种情况，动规一般采用正推的方式，即先求 f(0)到 f(5), 在已求出的项基础上 再逐项加上 0..5得到新加入一个骰子的和，逐个加入哈。

一般情况下动规为了避免越界都会采用这种正推的方式，避免越界的问题


dp\[i\] 表示当前点数和为 i 的概率。temp数组 记录当前骰子数目 i 可组合的所有点数和的概率。

题目中提到，i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

那么 temp, dp\[0\] 下标记录的不是 0 的概率，而是当前骰子数目所能组合的最小点数和的概率。举个栗子，两个骰子，
可以组合的最小点数和 2，那么 dp\[0\] = 1 / 36。 

最后一个小细节，为什么 temp 的 size 为 5n + 1呢？
每次加入一个骰子后，总共骰子 n 可以组成的和（包含重复）为 5n + 1. 因为哈， n 个骰子每个最小的值都是 1，和就是 n，不可能 < n - 1。那么总共可能出现和的种类为 6 * n - (n - 1) = 5n + 1

```kotlin
class Solution {
    fun dicesProbability(n: Int): DoubleArray {
        if (n <= 0) return doubleArrayOf()
        var dp = DoubleArray(6) { 1.0 / 6 }
        for (i in 2..n) {
            val temp = DoubleArray(5 * i + 1)
            for (j in dp.indices)
                for (k in 0..5)
                    temp[j + k] += dp[j] / 6.0
            dp = temp
        }
        return dp
    }
}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
