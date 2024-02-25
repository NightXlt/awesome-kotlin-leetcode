# [Remove Duplicates from Sorted Array II][title]

## Solution
因为给定数组是有序的，所以相同元素必然连续。我们可以使用双指针解决本题，遍历数组检查每
一个元素是否应该被保留，如果应该被保留，就将其移动到指定位置。具体地，我们定义两个指针
slow 和 fast 分别为慢指针和快指针，其中慢指针表示处理出的数组的长度，快指针表示已经检查过
的数组的长度，即 nums[fast]表示待检查的第一个元素，nums[slow -1］为上一个应该被保留的元
素所移动到的指定位置。
因为本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素
nums[slow -2]是否和当前待检查元素 nums[fast] 相同。当且仅当 nums[slow -2] = nums[fast]时，
当前待检查元素 nums[fast] 不应该被保留（因为此时必然有 nusns[slow -2] = nums[slow - 1] = nums[fast]。

最后，slow 即为处理好的数组的长度。
特别地，数组的前两个数必然可以被保留，因此对于长度不超过2的数组，我们无需进行任何处 理，对于长度超过2的数组，我们直接将双指针的初始值设为2即可。

```kotlin
class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size <= 2) return nums.size
        var slow = 2
        var fast = 2
        while (fast < nums.size) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast]
                slow++
            }
            fast++
        }
        return slow
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
