# [House Robber II][title]

## Solution
选了第二家， 就不能访问第一家。对这两种情况求结果得到的最大值即为所求。
具体解法和 [house robber I](https://leetcode.cn/problems/house-robber/) 类似

小偷在标号为i的房屋前有两个选择。一个选择是他进去偷东西。由于街道上有报警系统，因此他不能进入相邻的标号为i-1的房屋内偷东西，之前他最多能偷取的财物的最大值是f（i-2）。
因此，小偷如果进入标号为i的房屋并盗窃，他最多能偷得f（i-2）+nums[i]（nums是表示房屋内财物数量的数组）。
另一个选择是小偷不进入标号为i的房屋，那么他可以进入标号为i-1的房屋内偷东西，因此此时他最多能偷取的财物的数量为f（i-1）。那么小偷在到达标号为i的房屋时他能偷取的财物的最大值就是两个选项的最大值，即f（i）=max（f（i-2）+nums[i]，f（i-1）），这就是解决这个问题的状态转移方程。

实际实现中
用两个下标 first 和 second 分别指向 两个元素的最左侧，以及两个中的最大元素。
遍历过程中 
temp = second
second = max(second, first + nums[i])
first = temp 这样最终遍历结束的 second 即为最大值 
```kotlin
class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) {
            return nums[0];
        } else if (nums.size == 2) {
            return max(nums[0], nums[1]);
        }
        return max(
            helper(nums, 0, nums.size - 2),
            helper(nums, 1, nums.size - 1)
        )
    }

    fun helper(nums: IntArray, start: Int, end: Int): Int {
        var first = nums[start]
        var second = max(nums[start], nums[end])
        for (i in start + 2..end) {
            val temp = second
            second = max(second, first + nums[i])
            first = temp
        }
        return second
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/coin-change/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
