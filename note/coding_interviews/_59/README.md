#[滑动窗口的最大值][title]

## Solution
参考题解：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/solution/mian-shi-ti-59-i-hua-dong-chuang-kou-de-zui-da-1-6/

通过单调队列记录下当前窗口的最大值，最大值位于队列的顶端。数据结构选择双端队列实现因为其增删首尾节点的时间复杂度为O(1)
首先遍历数组的第一个窗口，得到单调递减序列，包含最大值，可能包含次最大值。
这里需要记录一个递减序列，因为当最左侧元素恰好出队时，接下来的队首元素会是次大值，通过与滑入窗口的值比较决定队首是否是最大值。如果是
```
example
nums = [1,3,-1-3,5,3] k = 3

index = 0, 判断队列中有没有比1小的元素，有的话出栈，因为队列为空，没有元素比他小，就直接入队
index = 1, 判断队列中有没有比3小的元素，有，把1出队，维持一个单调递减队列，再将3入队
index = 2, 判断队列中有没有比-1小的元素，因为队首元素是3 > 1，没有则直接入队

最终第一个构建的窗口队列中元素为 3，-1

得到第一个窗口最大值 res[0]=3
开始滑动窗口
index = 0 滑出窗口，index=3滑入窗口， 判断队列queue=[3,-1]中没有比-3更小的元素, 将-3直接入队. res[1]=3
index = 1 滑出窗口，index=4滑入窗口， 判断队列queue=[3,-1,-3]中没有比5更小的元素, 因为所有元素小于5这里会将队清空，再将5入队。quee=[5]. res[2]=5
index = 2 滑出窗口，index=5滑入窗口， 判断队列queue=[5]中没有比3更小的元素, 没有则将3直接入队 res[3]=3
```


```kotlin
class Solution {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        // If k is bigger than nums' size, index out of exception would happen.
        if (nums.isEmpty() || k - 1 !in nums.indices) return intArrayOf()
        val res = IntArray(nums.size - k + 1)
        // LinkedList is better than arraydequeue for adding and removing op.
        val dequeue: Deque<Int> = LinkedList<Int>()
        // construct first window
        for (i in 0 until k) {
            while (dequeue.isNotEmpty() && dequeue.last < nums[i]) {
                dequeue.removeLast()
            }
            dequeue.add(nums[i])
        }
        res[0] = dequeue.first
        // move window
        for (i in k until nums.size) {
            // removed element is the first element of queue
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
