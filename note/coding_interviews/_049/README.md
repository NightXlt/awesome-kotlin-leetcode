# [丑数][title]

## Solution
假设数组中已经有若干丑数了，最大的丑数为 M, 下一次丑数应该是数组中的丑数 * 2，3，5得到结果的最小值 > M。 并把最小值添加到数组中。
接下来更新2，3，5索引指针，因为小于等于 M的丑数都已经在索引中了，防止重复添加，当索引指向的值 * 2，3，，5 小于 M 时，就不停更新索引指针
之所以取最小值是保证其左侧的丑数都已经在数组中了。
为了记录* 2，3，5的指针，需要三个 index. 分别索引至 index == n.
值得注意的是，剑指 offer 上使用的是 while 更新2，3，5索引指针.但其实可以换为 if。因为每次更新数据是渐进的，相互之间乘积相差不会太大，
可以断点发现 while 循环，每次更新只会走一次。保证了每个索引指针乘一次，更新后就不满足条件。


```kotlin
class Solution {
    fun nthUglyNumber(n: Int): Int {
        if (n <= 0) return 0
        val result = IntArray(n)
        result[0] = 1
        var nextUglyNumIndex = 1
        var pMultiply2 = 0
        var pMultiply3 = 0
        var pMultiply5 = 0
        while (nextUglyNumIndex < n) {
            val nextUglyNum = min(result[pMultiply2] * 2, result[pMultiply3] * 3, result[pMultiply5] * 5) 
            result[nextUglyNumIndex] = nextUglyNum
            while (result[pMultiply2] * 2 <= nextUglyNum) { // 可换为 if
                pMultiply2++
            }
            while (result[pMultiply3] * 3 <= nextUglyNum) {
                pMultiply3++
            }
            while (result[pMultiply5] * 5 <= nextUglyNum) {
                pMultiply5++
            }
            nextUglyNumIndex++
        }
        return result[n - 1]
    }

    private fun min(i: Int, i1: Int, i2: Int): Int {
        return min(min(i, i1), i2)
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode-cn.com/problems/chou-shu-lcof/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
