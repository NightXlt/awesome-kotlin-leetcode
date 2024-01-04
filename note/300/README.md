# [Longest Increasing Subsequence][title]

## Solution
这道题之前写过，但这次写 dp + 二分完全没思路。纯粹的 dp 还是可以实现。 O(n^2) 吧。 先从纯 dp 记下
dp[i]: 以 nums[i] 结尾的最长递增序列。
dp 元素初始化值为 1， 遍历每个元素， 访问到时， 再内循环遍历比他小的元素， 并尝试更新 dp 的值。

```text
  dp[i] = max(dp[i], dp[j] + 1)
```
每一轮更新完尝试更新 res.

TimeComplexity: O(n^2)
SpaceComplexity: O(n)
```kotlin
import kotlin.math.max

fun lengthOfLIS(nums: IntArray): Int {
    val dp = IntArray(nums.size) { 1 }
    if (nums.isEmpty()) return 0
    var res = Int.MIN_VALUE
    for (i in nums.indices) {
        for (j in 0..<i) {
            if (nums[j] < nums[i]) {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
        res = max(dp[i], res)
    }
    return res
}
```

上面的慢是内循环寻找比 nums[i] 小的元素需要从头找一遍，
如果维护一个索引 （0..i-1） 的有序序列， 那么访问 i 下标时， 直接二分有序数组， 如果大于最大值，则追加进数组，并更新最长长度。
否则**一定**要更新数组内对应位置的值，因为这可能会影响到后续的放置。
dp[i] 表示： 包含 nums[i] 的最长序列中下标为 i 的元素。

> 如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。

这也是为啥每次要将尽可能小的数字更新到数组里的原因。
TimeComplexity：O(nlogn)
SpaceComplexity: O(n)

```kotlin
class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        var res = 0
        for (num in nums) {
            var left = 0
            // right 指向最新的右边界，最终会指向插入位置前面一位
            var right = res
            while (left < right) {
                val mid = (left + right) shr 1
                when {
                    
                    dp[mid] >= num -> right = mid
                    else -> left = mid + 1
                }
            }
            dp[left] = num
            // 如果比 dp 最后的元素都大，则更新长度
            if (res == right) {
                res++
            }
        }
        return res
    }
}
```


## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
