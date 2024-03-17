# [First Missing Positive][title]

因为数组元素是 [1..n] 排列, 其中有部分元素放到了错误元素.尝试通过交换的方式, 使得每个元素放到正确的位置, 这样第一个不满足 nums[i] == i + 1 的元素就是答案. 
如果所有元素都满足该条件, 说明缺失的元素是 n + 1.

```kotlin
class Solution {
    fun firstMissingPositive(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        if (!nums.contains(1)) return 1
        for (i in nums.indices) {
            while (nums[i] in 1..nums.size && nums[nums[i] - 1] != nums[i]) {
                nums[i] = nums[nums[i] - 1].also { nums[nums[i] - 1] = nums[i] }
            }
        }
        for (i in nums.indices) {
            if (i + 1 != nums[i]) {
                return i + 1
            }
        }
        return nums.size + 1
    }
}

```

时间复杂度： O(n) 空间复杂度：O(1)

更直接的解法是用一个 O(n) 的 hashmap, 记录每个元素的 num 和 下标的元素. 然后再遍历一次 1..n, 看第一个缺失在 map 的元素就知道了.

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/first-missing-positive/description
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
