# [Next Greater Element II][title]
在一个数组里找到当前元素右侧第一个大于他的数

## Solution
与 503 类似维护一个单调递减的栈，
对数组进行逆序访问， 逆序是为了方便获得当前元素的右侧情况， 而 503 是循环数组所以会不一样。
如果当前元素比栈顶下标指向的元素更大时， 我们循环出栈直至栈空或者不满足条件。
此外记住 stack 进去的是索引而非值。
```kotlin
    fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    if (nums2.isEmpty()) return intArrayOf()
    val map = mutableMapOf<Int, Int>()
    val stack = ArrayDeque<Int>()
    for (i in nums2.indices.reversed()) {
        while (stack.isNotEmpty() && nums2[i] > nums2[stack.last()]) {
            stack.removeLast()
        }
        map[nums2[i]] = if (stack.isNotEmpty()) nums2[stack.last()] else -1
        stack.add(i)
    }
    val res = IntArray(nums1.size)
    for (i in nums1.indices) {
        res[i] = map.getOrDefault(nums1[i], -1)
    }
    return res
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/next-greater-element-ii/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
