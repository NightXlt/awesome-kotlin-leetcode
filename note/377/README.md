# [Coin Change][title]

## Solution
背包问题, 组合

dp\[i\]：表示和为 i 的排列的数目, 为了得到和为i的排列，
有如下选择：在和为 i-nums\[0\] 的排列中添加标号为 0 的数字，此时 f（i） 等于 f（i-nums\[0]）
在和为 i-nums\[1] 的排列中添加标号为 1 的数字，此时 f（i） 等于 f（i-nums\[1]）。 以此类推，在和为 i-nums\[n-1] 的排列中添加标号为 n-1 的数字（n为数组的长度），
此时 f(i) 等于f(i-nums\[n-1])。因为目标是求出所有和为 i 的排列的数目，所以将上述所有情况全部累加起来。该状态转移方程可以表示为
```
 dp[i] = ∑(dp[i-num])
```
题目可以归类为 组合背包(考虑顺序)：模板是: 外循环 target,内循环nums,target正序且 target>=nums\[i];
时间复杂度：O(nt) // t 为 硬币数目, n 为 coins 长度
空间复杂度：O(t)
```kotlin
class Solution {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1 // 空排列为 1
        for (i in 1 .. target) {
            for (num in nums) {
                if (i >= num) {
                    dp[i] += dp[i - num]
                }
            }
        }
        return dp[target]
    }
}


```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/coin-change/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
