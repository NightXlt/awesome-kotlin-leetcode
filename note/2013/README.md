# [Detect Squares][title]

## Solution
使用嵌套的 HashMap 记录下每个 x， y, count 的映射关系， add 时直接加入 map 就好，
count 时， 遍历 x 下的所有 y1, 得到 len = y - y1, count1 = count[y1]. 因为是正方形, x 可能会是 [x - len, x + len]。
遍历可能的 x 得到 x1. 得到对应 （x1，y）和 （x1，y1） 两个 count2, count3. 累加到 res 上就是最终结果。

```kotlin
class DetectSquares() {

    val rowToCol = mutableMapOf<Int, MutableMap<Int, Int>>()

    fun add(point: IntArray) {
        val (x, y) = point
        val colToCount = rowToCol.getOrPut(x) { mutableMapOf<Int, Int>() }
        colToCount.merge(y, 1) { a,b -> a + b }
    }

    fun count(point: IntArray): Int {
        val (x, y) = point
        var res = 0
        val colToCount = rowToCol.getOrDefault(x, mutableMapOf<Int, Int>())
        for (y1 in colToCount.keys) {
            if (y1 == y) continue
            val c1 = colToCount.getValue(y1)
            val len = y - y1
            val xRanges = intArrayOf(x - len, x + len)
            for (x1 in xRanges) {
                val colToCount1 = rowToCol.getOrDefault(x1, mutableMapOf<Int, Int>())
                val c2 = colToCount1.getOrDefault(y, 0)
                val c3 = colToCount1.getOrDefault(y1, 0)
                res += c1 * c2 * c3
            }
        }
        return res
    }

}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * var obj = DetectSquares()
 * obj.add(point)
 * var param_2 = obj.count(point)
 */
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/detect-squares/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
