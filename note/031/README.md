# [Next Permutation][title]

## Solution
希望下一个排列增大，但希望增大的幅度尽可能小。当是最大排列时，变为最小排列
- 将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
- 保证增大幅度尽可能小。「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。

举个栗子 (4,5,2,6,3,1)
 
 倒序找到第一对 升序序列 a\[i\] < a\[i + 1\]. i = 2, a\[i\] = 2, i 右侧均为降序，因为右侧都不满足 a\[i\] > a\[i + 1\]
  
  找到 i 右侧`第一个`比a\[i\]大的下标 j. 即 j = 4, a\[j\] = 3, 请注意这时的 a\[j - 1\] = 6 > a\[j\] = 3 > a\[i\] = 2 > a\[j + 1\] = 1 
  
  将 a\[i\] 与 a\[j\]数字进行交换。交换后 a\[i\] 右侧所有数字仍旧保持为降序， a\[j - 1\] = 6 > a\[j\] = 2 > a\[j + 1\] = 1
  
  将 a\[i\]右侧数字排序，使得变动尽可能小，同时也保证了在最大序列后增大变为最小序列

```kotlin
class Solution {
    fun nextPermutation(nums: IntArray) {
        var i = nums.size - 2
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--
        }
        if (i >= 0) {
            var j = nums.lastIndex
            while (j >= 0 && nums[i] >= nums[j]) {
                j--
            }
            nums[i] = nums[j].also { nums[j] = nums[i] }
        }
        reverse(nums, i + 1)
    }

    private fun reverse(nums: IntArray, start: Int) {
        var (start, end) = start to nums.lastIndex
        while (start < end) {
            nums[start] = nums[end].also { nums[end] = nums[start] }
            start++
            end--
        }
    }
}
```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.com/problems/next-permutation
[ajl]: https://github.com/NightXlt/awesome-kotlin-leetcode
