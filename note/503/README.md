# [Next Greater Element II][title]
在一个循环的数组里找到当前元素右侧第一个大于他的数

## Solution
看到第一眼有点想用最小堆去做， 后面想了想可以用 TreeSet 做， 有 floor 可以拿到最接近当前的值，
但 TreeSet 是不允许重复的， 所以这个方法也行不通.
后面看了题解是用了单调栈去解决， 类似每日温度拿到题目。
维护一个单调递减的序列， 如果遇到比栈顶下标指向的元素更大时， 我们循环出栈直至栈空或者不满足条件。
针对循环数组， 可以通过对索引取模实现。而要访问每个元素的右侧元素， 仅需遍历两次即可。

此外值得留意的是， 加到 stack 的索引要经过取模。

```kotlin
class Solution {
    fun nextGreaterElements(nums: IntArray): IntArray {
        if (nums.isEmpty()) return intArrayOf()
        val res = IntArray(nums.size) { -1 }
        val n = nums.size
        val stack = ArrayDeque<Int>()
        for (i in 0..<2 * nums.size) {
            while (stack.isNotEmpty() && nums[i % n] > nums[stack.last()]) {
                val cur = stack.removeLast()
                res[cur] = nums[i % n]
            }
            stack.add(i % n)
        }
        return res
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/next-greater-element-ii/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
