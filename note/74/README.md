# [Search a 2D Matrix][title]

这道题印象中的解法在以右上角作为起点进行搜索，时间复杂度是 O(m + n). 
但在题解中要求是以 O(logmn) ， 那就需要通过二分查找实现。

首先二分查找确定第一列中比 target 小的元素所在的行，再对这行进行一次二分查找。 共计 O(logm + logn) = O(logmn)
值得注意的是怎么二分找到第一个比 target 小的元素，以及 if 判断， 初始化的 left 值， 计算长度的运算。

```kotlin
class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty()) return false
        val rowIndex = searchFirstCol(matrix, target)
        if (rowIndex < 0) {
            return false
        }
        return searchRow(matrix[rowIndex], target)
    }

    private fun searchRow(nums: IntArray, target: Int): Boolean {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = left + ((right - left) shr 1)
            when {
                nums[mid] == target -> return true
                nums[mid] > target -> right = mid - 1
                else -> left = mid + 1
            }
        }
        return false
    }

    private fun searchFirstCol(matrix: Array<IntArray>, target: Int): Int {
        // left 初始化为 -1 目的是找到 <= target 的元素
        var left = -1
        var right = matrix.lastIndex
        // 当 right <= left 时， 退出循环
        while (left < right) {
            // right - left + 1 求 length
            val mid = left + ((right - left + 1) shr 1)
            // matrix > target，移动 right 为 mid - 1, 当前 mid 不是 < target 元素
            if (matrix[mid][0] > target) {
                right = mid - 1
            } else {
                // 否则 left >= mid, 当前 mid 可能为满足要求索引。
                left = mid
            }
        }
        return left
    }
}
```


## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/search-a-2d-matrix/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
