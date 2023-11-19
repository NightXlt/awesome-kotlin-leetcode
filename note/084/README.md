# [Largest Rectangle in Histogram][title]

## Description

## 思路
暴力解法是穷举所有可能的矩形面积, 求最大值,
先反应出暴力解法,再想更优解.

这道题如果能求出来了以每个柱为高的最大面积, 取个最大值就是最终结果.
高是知道了, 但宽不好确定啊. 题解中提到的单调栈可推断知道, 为什么呢

如果我们维护了一个高度单调递增对应的下标集合, 那么在遇到比栈中最大的元素要小的元素要进栈时,
会需要出栈, 出栈后,这时我们去计算以当前出栈的柱子为高的最大面积,

那么 「即将进栈的当前下标 - 出栈后栈中最大元素 + 1」 即为柱子的宽度

例子:
```
2, 5, 4, 6, 1 为例子

- 2 的下标 0 先进栈
- 5 比 2 大 下标 1 直接进栈
- 4 比 5 小, 直接出栈 5 的下标 1, 出栈后栈顶下标为 0, 
    那么以 5 为柱子高度, 他的面积是元素 4 的下标 2 - 栈顶下标 0 - 1= 1, 
    因此以 5 为柱子高度的面积为 5.  再将  4 的下标 2 入栈
- 6 比 4 大 下标 3 直接进栈
- 1 比 6 小 进行出栈, 计算以 6 为柱子面积,逻辑与 5 类似
- 1 比 4 小 进行出栈, 计算以 4 为柱子面积, 直接出栈 4 的下标 2, 出栈后栈顶下标为 0, 
    那么以 4 为柱子高度, 他的面积是元素 1 的下标 4 - 栈顶下标 0 - 1= 3, 
    因此以 4 为柱子高度的面积为 3*4 = 12, 取最大值
- 1 比 2 小 进行出栈, 计算以 2 为柱子面积,逻辑与 5 类似
```


小细节:
1. 计算 width 时之所以需要 -1， 是因为当前要入栈的元素，并不是要计算矩形宽的一部分， i - 1 才是下标的开始，下标的结束是 stack 的 last
2. 栈顶预置了一个 -1, 因此判断循环结束是栈顶 != -1, 
   -1 在遇到算长度时比较方便, 比如只有两个元素, 较小的在后面时, 如 5, 4, 出栈到 -1 时, 得到的宽度是数组长度 2
2.  栈中元素在遍历结束后, size 可能 > 1, 比如 5, 4 这样的集合
3. ArrayDequeue 当栈的时候, 记得是 removeLast, last() 呀, 获取栈顶

```kotlin
class Solution {
    fun largestRectangleArea(heights: IntArray): Int {
        val stack = ArrayDeque<Int>()
        stack.add(-1)
        // 计算以每个柱子为顶的矩形面积
        var maxArea = 0
        for (i in heights.indices) {
            while (stack.last() != -1 && heights[stack.last()] >= heights[i]) {
                val height = heights[stack.removeLast()]
                val width = i - stack.last() - 1
                maxArea = max(maxArea, height * width)
            }
            stack.add(i)
        }
        while (stack.last() != -1) {
            val height = heights[stack.removeLast()]
            val width = heights.size - stack.last() - 1
            maxArea = max(maxArea, height * width)
        }
        return maxArea
    }
}

```




## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
