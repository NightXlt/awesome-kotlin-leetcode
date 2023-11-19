# [Daily Temperatures][title]

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. 
If there is no future day for which this is possible, keep answer[i] == 0 instead.

```

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
```

# 思路
要找到当前温度后面比其大的温度，可以通过维护一个单调栈的方式， 栈内顺序单调递减， 这样遇到比栈顶元素更大的元素要进栈时，
循环出栈栈内的元素，二者下标相减得到的就是需要等待天数。其次栈内记录的时数组下标， 便于直接索引数组元素。

```kotlin
class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        val answers = IntArray(temperatures.size)
        for (i in temperatures.indices) {
            while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.last()]) {
                val prev = stack.removeLast()
                answers[prev] = i - prev
            }
            stack.add(i)
        }
        return answers
    }
}
```

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/daily-temperatures/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
