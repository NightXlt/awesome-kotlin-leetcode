# [Target Sum][title]

## Solution
0-1背包问题。因为每个数字只能选一次，对应的模板是  外循环nums,内循环target,target倒序且target>=nums[i];

给数组里的每个数字添加正负号得到target，那么所有数字的和为sum, 目标值为tar

其中挑选出的正数项的和为x > 0, 负数项的和为y > 0.那么 x - y = target
而 x + y = sum 两式相减得到 (sum - target) / 2 = y。

因此 y 大于零，y 一定是个偶数。如果不满足这个条件表明不存在一个正负序列和为target。

用函数 f（i，j）表示在数组的前i个数字（即nums[0..i-1]）中选出若干数字使和等于j的方法的数目。
如果数组的长度为n，目标和为t，那么f（n，t）就是整个问题的解。

这道题的递推公式和 416 类似:
```
f（i，j） = f(i - 1, j) + f(i - 1, j - nums[i -1])   
```

用一维数组来替代呐, 就是下面的代码了

dp[i] 表示和为 i 的 num 组合有多少种。

递推公式：dp[i] = dp[i] + dp[i - num]   i>= num

```kotlin
class Solution {

    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        val diff = sum - target
        if (diff < 0 || diff % 2 != 0) return 0
        val neg = diff / 2
        val dp = IntArray(neg + 1)
        dp[0] = 1
        for (num in nums) {
            for (j in neg downTo num) {
                dp[j] += dp[j - num]
            }
        }
        return dp[neg]
    }
}
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/lru-cache/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
