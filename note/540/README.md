# [Single Element in a Sorted Array][title]

## Solution
因为是排序数组，虽然之前的异或思想还能用，但要求是 O(logn) 就达不到要求了
因为是排序 + logn 复杂度，自然想到二分查找。
如果我们二分找到一对数字， 其本身不相等，但其前后一对数字相等，就说明我们找到了唯一的数字，
而且是当前这对数字前面的哪个，因为后面的哪个和更后面的一对能够组成一对的。
因为要找的是一对数字，我们的 right 要调整为 length / 2.
这样得到的就是 mid 就是第几对数字。 mid * 2 得到是这对数字的开始索引， 
但要注意的是访问 mid * 2 + 1 可能会越界，所以我们保证 mid * 2 < length.
这样可能漏掉最后一个元素是解的可能性，可能放在循环外考虑。
此外访问到 0 时可以直接返回当前这对数字的第一个。
当跳出循环时说明数组的最后一个元素是非重复元素

```kotlin
class Solution {
    fun singleNonDuplicate(nums: IntArray): Int {
        var left = 0
        var right = nums.size shr 1
        while (left <= right) {
            val mid = (left + right) shr 1
            val i = mid * 2

            if (i < nums.size - 1 && nums[i] != nums[i + 1]) {
                // 如果 mid = 0 的话那势必是第一（i）个元素是非重复了
                // mid != 0 时， i 至少 >= 2, 所以这里的 - 是安全的
                if (mid == 0 || nums[i - 2] == nums[i - 1]) return nums[i]
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return nums.last()
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/single-element-in-a-sorted-array/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
