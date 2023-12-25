# [Maximum Strictly Increasing Cells in a Matrix][title]

## Solution
按元素值从小往大计算。 定义 f[i] 表示到达 mat[i][j] 时所访问的单元格的最大数量。那么答案就是所有 f[i][j] 的最大
值。
如何计算 f[i][j] 从哪转移过来？
请注意，我们不需要知道具体从哪个单元格转移过来，只需要知道转移来源的f的最大值是多少。

按照元素值从小往大计算，那么第 i 行的比 mat[i][j] 小的f值都算出来了，大于等于 mat[i][j] 的尚未计算，视作0。
所以对于第 i 行，相当于取这一行的f值的最大值，作转移来源的值。我们用一个长为 m 的数组 rowMax 维护每一行的f值的最大值。
对于每一列，也同理，用一个长为n的数组 colMax 维护。

所以有 f[i][j] = max（rowMax[i]， colMax[j]） + 1

这里加一是把 mat[i][j] 算上。

代码实现时 f[i][j] 可以省略，因为只需要每行每列的f值的最大值。
对于相同元素，在全部计算出最大值后，再更新到 rowMax 和 colMax 中。

```kotlin
import java.util.*
import kotlin.collections.ArrayDeque

class MKAverage(private val m: Int, private val k: Int) {

    private var sum = 0L

    private val queue = ArrayDeque<Int>()

    private val countMap = TreeMap<Int, Int>()

    fun addElement(num: Int) {
        countMap.merge(num, 1, Integer::sum)
        queue.add(num)
        sum += num
        if (queue.size <= m) return
        val lruElement = queue.removeFirst()
        sum -= lruElement
        countMap.merge(lruElement, -1, Integer::sum)
        if (countMap[lruElement] == 0) {
            countMap.remove(lruElement)
        }
    }

    fun calculateMKAverage(): Int {
        if (queue.size < m) return -1
        return ((sum - getSumMaxK() - getSumMinK()) / (m - 2 * k)).toInt()
    }


    private fun getSumMaxK(): Long {
        var kSum = 0L
        var max = Int.MAX_VALUE
        var i = k
        while (i > 0) {
            val (value, count) = countMap.floorEntry(max)
            if (count > i) {
                kSum += i * value
                return kSum
            }
            kSum += count * value
            i -= count
            max = value - 1
        }
        return kSum
    }

    private fun getSumMinK(): Long {
        var kSum = 0L
        var min = Int.MIN_VALUE
        var i = k
        while (i > 0) {
            val (value, count) = countMap.ceilingEntry(min)
            if (count > i) {
                kSum += i * value
                return kSum
            }
            kSum += count * value
            i -= count
            min = value + 1
        }
        return kSum
    }
}

```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/maximum-strictly-increasing-cells-in-a-matrix/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
