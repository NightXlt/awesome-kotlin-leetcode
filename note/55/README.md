# [Jump Game][title]

## Solution
从起点开始， 记录右端最远可以到达的距离，如果模拟最终能到达终点即为结果。
每次访问一个在最远可到达距离的索引时， 尝试试试 i + nums[i] > rightMost, 如果可以则更新 rightMost 的值。

```kotlin
import kotlin.math.max

fun canJump(nums: IntArray): Boolean {
    if (nums.size <= 1) return true
    var rightMost = 0
    for (i in nums.indices) {
        if (i <= rightMost) {
            rightMost = max(rightMost, i + nums[i])
        }
        if (rightMost >= nums.lastIndex) {
            return true
        }
    }
    return false
}

```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
