#[滑动窗口的最大值][title]

## Solution


```kotlin
class Solution {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        // If k is bigger than nums' size, index out of exception would happen.
        if (nums.isEmpty() || k - 1 !in nums.indices) return intArrayOf()
        val res = IntArray(nums.size - k + 1)
        // LinkedList is better than arraydequeue for adding and removing op.
        val dequeue: Deque<Int> = LinkedList<Int>()
        for (i in 0 until k) {
            while (dequeue.isNotEmpty() && dequeue.last < nums[i]) {
                dequeue.removeLast()
            }
            dequeue.add(nums[i])
        }
        res[0] = dequeue.first
        for (i in k until nums.size) {
            if (dequeue.first == nums[i - k]) {
                dequeue.removeFirst()
            }
            while (dequeue.isNotEmpty() && dequeue.last < nums[i]) {
                dequeue.removeLast()
            }
            dequeue.add(nums[i])
            res[i - k + 1] = dequeue.first
        }
        return res
    }
}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/submissions/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
