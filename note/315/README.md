# [Count of Smaller Numbers After Self][title]
Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].



Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

## Solution
這道題和逆序对那道题类似，我们可以往归并方向思考
但不同的点在于我在进行归并时，如何找到排序后当前元素的原始位置，因为我需要记录结果到数组中，这是第一个点，我们可以记录每个元素的索引，每次元素交换时同时索引也跟着更新，那我们就可以以 O(1) 的时间访问到原始索引。
第二个点是当我找到 nums[i] <= nums[j] 时, 意味着 j 左边到 mid 位置的元素都是小于 j 的， 那 res[indexes[i]] += j - mid -1

```kotlin
class Solution {
    var copy = intArrayOf()
    var res = intArrayOf()
    var indexes = intArrayOf()
    var copyIndexes = intArrayOf()

    fun countSmaller(nums: IntArray): List<Int> {
        if (nums.isEmpty()) return emptyList()
        copy = IntArray(nums.size)
        res = IntArray(nums.size)
        indexes = IntArray(nums.size)
        copyIndexes = IntArray(nums.size)
        for (i in nums.indices) {
            indexes[i] = i
        }
        mergeSort(nums, 0, nums.size - 1)
        return res.toList()
    }

    private fun mergeSort(
        nums: IntArray,

        low: Int,
        high: Int
    ) {
        if (low >= high) return
        val mid = (low + high) shr 1 // 留意数组长度，其中 low + high是否可能溢出
        mergeSort(nums, low, mid)
        mergeSort(nums, mid + 1, high)
        merge(nums, low, mid, high)
    }

    private fun merge(nums: IntArray, low: Int, mid: Int, high: Int) {
        for (i in low..high) {
            copy[i] = nums[i]
        }
        var i = low
        var j = mid + 1
        var k = low
        while (i <= mid && j <= high) {
            if (copy[i] <= copy[j]) {
                copyIndexes[k] = indexes[i]
                res[indexes[i]] += (j - mid - 1)
                nums[k++] = copy[i++]
            } else {
                copyIndexes[k] = indexes[j]
                nums[k++] = copy[j++]
            }
        }
        while (i <= mid) {
            copyIndexes[k] = indexes[i]
            res[indexes[i]] += (j - mid - 1)
            nums[k++] = copy[i++]
        }
        while (j <= high) {
            copyIndexes[k] = indexes[j]
            nums[k++] = copy[j++]
        }
        for (i in low..high) {
            indexes[i] = copyIndexes[i]
        }
    }
}


```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/count-of-smaller-numbers-after-self/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
