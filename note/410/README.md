# [Split Array Largest Sum][title]

## Solution
「元素和的最大值」 越小，需要划分出的段数就越多，反之越少。
当存在一个满足条件的子数组和的最大值时，那么比这个最大值更大的子数组和的最大值一定也满足条件。

看个例子：
```text
Input: nums = [7,2,5,10,8], k = 2
Output: 18

那比 18 更大的最大值 20 一定也满足
由原先的 [7,2,5] [10, 8] 变为 [7, 5] [10, 8, 2]
只不过这样的子数组和并不是最小的。
```

我们可以通过类似找香蕉那道题的思路，二分找到 "size = k" 下的最小的数组和.

1. 左右边界的确定

右边界明显是整个数组的和，
左边界一方面可能是数组的最大值， 也可能是 sum / k 的值。 取这二者的最大值。
如果数组的最大值（10）比 sum / k （16）都要小时, 取 sum / k 是因为要满足分组为 k 的限制

2. 二分退出条件
left <= right. 如果满足条件 right = mid - 1, 否则 left = mid + 1.
最后退出循环条件的 left 就是最小值。类似搜索插入位置那道题。

3. check 的逻辑
模拟计算每个子数组的和， 如果发现 count 超过 k, 或者其中某个元素比 sum 都要大时， 返回 false, 表明当前的值小了。

T: O(N*logSum) N：是元素长度， Sum 是所有元素之和
S: O(1)

```kotlin
package com.blankj.hard._410

import kotlin.math.max

class Solution {
    fun splitArray(nums: IntArray, k: Int): Int {
        val max = nums.max()
        val sum = nums.sum()
        var left = max(max, sum / k)
        var right = sum
        while (left <= right) {
            val mid = left + ((right - left) shr 1)
            if (check(nums, mid, k)) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }

    private fun check(nums: IntArray, maxSum: Int, k: Int): Boolean {
        var count = 1
        var sum = 0
        for (num in nums) {
            if (sum + num <= maxSum) {
                sum += num
                continue
            }
            if (count == k || num > maxSum) {
                return false
            }
            count++
            sum = num
        }
        return true
    }
}

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/split-array-largest-sum/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
