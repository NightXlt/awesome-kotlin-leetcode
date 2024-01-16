# [K Empty Slots][title]

## Description

You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs are turned off. We turn on exactly one bulb every day until all bulbs are on after n days.

You are given an array bulbs of length n where bulbs[i] = x means that on the (i+1)th day, we will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.

Given an integer k, return the minimum day number such that there exists two turned on bulbs that have exactly k bulbs between them that are all turned off. If there isn't such day, return -1.


**Example 1:**


Input: bulbs = [1,3,2], k = 1
Output: 2
Explanation:
On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
We return 2 because on the second day, there were two on bulbs with one off bulb between them.

**Example 2:**


Input: bulbs = [1,2,3], k = 1
Output: -1

## Solution
暴力解法：通过 TreeSet 记录比当前灯泡序号前后最接近的灯泡序号， 如果两者之间差值在 k + 1 之内说明找到了结果了， 而且因为天数是从前往后找， 第一个满足条件的就是结果

Time: 因为使用了 TreeSet 让二叉搜索树保持平衡，需要 O(NlogN) 复杂度
Space: O(N)

```kotlin
    fun kEmptySlotsWithMuchTime(bulbs: IntArray, k: Int): Int {
        val set = TreeSet<Int>()
        for ((index, bulb) in bulbs.withIndex()) {
            val prev = set.floor(bulb)
            if (prev != null && abs(prev - bulb) - 1 == k) {
                return index + 1
            }
            val next = set.ceiling(bulb)
            if (next != null && abs(next - bulb) - 1 == k) {
                return index + 1
            }
            set.add(bulb)
        }
        return -1
    }
```

另一种解法有些晦涩， 核心思想是对于 bulbs 中的 k 个相邻块（“窗口”），如果此窗口的内部的开灯时间都大于左邻居和右邻居的开灯日期，我们知道它满足问题陈述中的条件。

首先 days 记录天数和灯泡序列号的关系，
通过单调栈记录单调递增， 在灯泡序列号递增的情况下， 灯泡出现天数的递增的序列。
同时维护了一个长度 K 的窗口，滑动窗口时，如果遇到 i-k-1 指向的是 k 左侧的下标， 如果其恰好是队列中最小元素时， 则进行出队
否则不进行出栈。 
滑动窗口时， 遇到当前灯泡序列号出现的天数比当前更大时， 为了维护单调递增，则进行循环出队。
如果此时栈为空， 而且 prev 是 i - k - 1 的话，说明 i 和 prev 的距离是 k. 而且更重要的背后意思是这两个灯泡之间没有其他亮着的灯泡。
比如 prev = 1 k =1 i =3； prev = i - k - 1 = 1

Time: O(N)
Space: O(N)

```kotlin
    fun kEmptySlots(bulbs: IntArray, k: Int): Int {
        val mins = ArrayDeque<Int>()
        val days = IntArray(bulbs.size)
        for ((i, bulb) in bulbs.withIndex()) {
            days[bulb - 1] = i + 1
        }
        repeat(min(k + 1, days.size)) { i ->
            while (mins.size != 0 && days[mins.last()] > days[i]) {
                mins.removeLast()
            }
            mins.addLast(i)
        }
        var ans = -1
        for (i in k + 1 until days.size) {
            val prev = mins.first()
            if (prev == i - k - 1) mins.removeFirst()
            while (mins.isNotEmpty() && days[mins.last()] > days[i]) {
                mins.removeLast()
            }
            if (mins.isEmpty() && prev == i - k - 1) {
                val possibleAns = max(days[prev], days[i])
                if (ans == -1 || possibleAns < ans) {
                    ans = possibleAns
                }
            }
            mins.addLast(i)
        }
        return ans
    }

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/k-empty-slots/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
