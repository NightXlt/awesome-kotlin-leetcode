# [Gas Station][title]

## Solution
累计剩余油量，找到最小的累计剩余油量， 记录其下标minIndex。
如果最终累计剩余油量小于零，表示不能开一圈，否则为 (minIndex + 1) % len.
 
取余是因为可能最小的累计剩余油量为最后一个索引，那么起始索引应该为 0
具体可参考这个题解所画的图，很直观.留意蓝色虚线内容。
https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/

```kotlin
class Solution {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var minSpare = Int.MAX_VALUE
        var minIndex = 0
        var spare = 0
        for (i in gas.indices) {
            spare += gas[i] - cost[i]
            if (spare < minSpare) {
                minIndex = i
                minSpare = spare
            }
        }
        return if (spare < 0) -1 else (minIndex + 1) % gas.size
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/gas-station/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
