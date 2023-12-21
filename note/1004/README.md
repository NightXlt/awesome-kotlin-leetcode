# [Max Consecutive Ones III][title]

## Solution
通过滑动窗口去模拟这个过程， left， right 记录左右边界, count 记录区间内的 0 的个数，
当发现 0 的个数超过 k 时， 开始不断移动左边界， 直至符合 count == k 的要求

里面有个技巧的点就是， 移动左边界时， 通过 1 - nums[i] 来统计 0 的个数， 一般不太好想到

```kotlin
class Solution {
    fun longestOnes(nums: IntArray, k: Int): Int {
        var res = 0
        var left = 0
        var count = 0
        for (right in nums.indices) {
            count += 1 - nums[right]
            while (count > k) {
                count -= 1 - nums[left++]
            }
            res = max(res, right - left + 1)
        }
        return res
    }
}

```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/race-car/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
