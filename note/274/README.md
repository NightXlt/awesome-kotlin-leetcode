# [H-Index][title]

## Solution
计数排序，
通过计数排序， 统计引用数的 count，针对引用数大于数组长度 n 的论文， 计算 h 指数时视作 n.
倒序遍历数组， 当数组的后缀和大于当前 index 时说明找到了最大的 h 指数。
```kotlin
class Solution {
    fun hIndex(citations: IntArray): Int {
        val count = IntArray(citations.size + 1)
        for (citation in citations) {
            if (citation > citations.size) {
                count[count.lastIndex]++
            } else {
                count[citation]++
            }
        }
        var total = 0
        for (h in count.indices.reversed()) {
            total += count[h]
            if (total >= h) {
                return h
            }
        }
        return 0
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/h-index/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
