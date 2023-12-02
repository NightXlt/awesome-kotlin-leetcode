# [Unique Paths][title]

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

## Solution
当i等于0时，机器人位于格子最上面的一行，机器人不可能从某个位置向下走一步到达一个行号i等于0的位置。 因此，f（0，j）等于1，即机器人只有一种方法可以到达坐标为（0，j）的位置，即从（0，j-1）的位置向右走一步。

当j等于0时，机器人位于格子最左边的一列，机器人不可能从某个位置向右走一步到达一个列号j为0的位置。因此，f（i，0）等于1，即机器人只有一种方法可以到达坐标为（i，0）的位置，即从（i-1，0）的位置向下走一步。

当行号i、列号j都大于0时，机器人有两种方法可以到达坐标为（i，j）的位置。
它既可以从坐标为（i-1，j）的位置向下走一步，也可以从坐标为（i，j-1）的位置向右走一步，因此，f（i，j）等于f（i-1，j）与f（i，j-1）之和。

因为 f（i，j）只依赖 f（i-1，j）与f（i，j-1）.  我们可以用个 1 维数组逐行遍历模拟.

上述代码中的dp是一个一维数组，f（i-1，j）和f（i，j）都保存在“dp[j]”中。

dp[j] += dp[j - 1]
仍然用一个二重循环按照状态转移方程计算，“dp[j]+=dp[j-1]”可以看成“dp[j]=dp[j]+dp[j-1]”。在赋值运算符的右边，“dp[j]”中保存的是 f（i-1，j），“dp[j-1]”中保存的是f（i，j-1）。在计算f（i，j）之前，按照从左到右的顺序f（i，j-1）的值已经计算出来并保存在“dp[j-1]”中。

用f（i-1，j）和 f（i，j-1）的值计算出f（i，j）之后将结果保存到“dp[j]”中。
虽然之前保存在“dp[j]”中的 f（i-1，j）的值被覆盖了，但由于这个值不再需要，因此覆盖这个值并不会出现任何问题。

```kotlin
    fun uniquePathsWithLessSpace(m: Int, n: Int): Int {
 val dp = IntArray(n) { 1 }
 for (i in 1 until m) {
  for (j in 1 until n) {
   dp[j] += dp[j - 1]
  }
 }
 return dp[n - 1]
}

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/unique-paths/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
