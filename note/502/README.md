# [IPO][title]

## Description
给定 k 笔投资限制及初始资本，获取到最高利润 

## Solution
通过数组记录所有的资本和利润， 数组按照资本进行排序。
优先级队列 堆 用来方便去到最大的利润，
循环 k 次， 将符合当前资本内的利润都加入到队列中， 随后从中取出最大的一笔利润更新到资本中

```text
  res += queue.poll()
```

随后， 又开始循环，这时使用的资本是更新后的 res. 而不是原始的 w.
最终如果 queue.isEmpty 跳出或者循环结束则返回结果。


```kotlin
import java.util.*

class Solution {
    fun findMaximizedCapital(
        k: Int,
        w: Int,
        profits: IntArray,
        capital: IntArray
    ): Int {
        val arr = Array(profits.size) { IntArray(2) }
        for (i in profits.indices) {
            arr[i][0] = capital[i]
            arr[i][1] = profits[i]
        }
        arr.sortBy { it[0] }
        val queue = PriorityQueue<Int> { o1, o2 -> o2 - o1 }
        var cur = 0
        var res = w
        for (i in 1..k) {
            while (cur < profits.size && arr[cur][0] <= res) {
                queue.add(arr[cur][1])
                cur++
            }
            if (queue.isNotEmpty()) {
                res += queue.poll()
            } else {
                break
            }
        }
        return res
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/ipo/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
