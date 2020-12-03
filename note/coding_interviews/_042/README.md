# [最大子序和][title]

## Solution
https://nightxlt.github.io/2019/01/08/the%20contiguous%20subarray%20which%20has%20the%20largest%20sum/
```kotlin
class Solution {
    fun maxSubArrayDP(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        dp[0] = nums[0]
        var maxSum = dp[0]
        for (i in 1 until nums.size) {
            dp[i] = if (dp[i - 1] >= 0) dp[i - 1] + nums[i] else nums[i]
            maxSum = max(dp[i], maxSum)
        }
        return maxSum
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/maximum-subarray/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
