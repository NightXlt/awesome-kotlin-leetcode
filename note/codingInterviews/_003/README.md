# [数组中重复的数字][title]

## Solution
长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内是关键点，这意味着有重复的情况下，将数组进行排序，必然有数字和他的下标不等。
举个例子 
```
下标：0, 1, 2, 3，4, 5, 6
数组：2, 3, 1, 0, 2, 5, 3
排序: 0, 1, 2, 2, 3, 3, 5
```
我们观察数组下标i = 3时开始 nums\[i\] != i. 那么遍历时，通过将 nums\[i\]放到第 nums\[i\]号位置以达到有序。当发现第一个 nums\[i\] == nums\[nums\[i\]\]时说明出现了重复元素，直接返回
```
class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        var i = 0
        while (i < nums.size) {
            if (nums[i] == i) {
                i++
                continue
            }
            if (nums[i] == nums[nums[i]]) return nums[i]
           nums[i] = nums[nums[i]].also { nums[nums[i]] = nums[i] }
        }
        return -1
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
