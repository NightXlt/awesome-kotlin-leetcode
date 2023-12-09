# [Minimum Size Subarray Sum][title]

## Description

Given an array of positive integers nums and a positive integer target, return the minimal length of a
subarray
whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
**Example 1:**

```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
```

**Example 2:**

```
Input: target = 4, nums = [1,4,4]
Output: 1
```
Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).


## 思路
双指针 + 滑动窗口思路, i, j 分别指向数组的开头. 累加从 0 到 j 的 sum. 当发现 sum > target 时, 开始收缩左侧滑动窗口, 同时更新 sum, 直至 i 收缩到 j, 或者 sum < target, 才会再开始向右移动 j.
此外就是记录结果的 min 可能找不到符合目标的结果, 发现是初始值的情况需要返回 0.
```kotlin
class Solution {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        if (nums.isEmpty()) error("Empty array!")
        var minLength = Int.MAX_VALUE
        var i = 0
        var sum = 0
        for (j in nums.indices) {
            sum += nums[j]
            while (i <= j && sum >= target) {
                minLength = min(j - i + 1, minLength)
                sum -= nums[i]
                i++
            }
        }
        return if (minLength == Int.MAX_VALUE) 0 else minLength
    }

}
```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/minimum-size-subarray-sum/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
