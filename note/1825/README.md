# [Finding MK Average][title]

## Solution
1、 TreeMap 统计计数；
2、 Queue 列队保存最后的 m 个元素；
3、 sum 所有元素的总和；
4、 利用 TreeMap 的 ceilingEntry、floorEntry 方法可以很容易的找到 k 个最大数、最小数，并计算对应的总和；


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



[title]: https://leetcode.cn/problems/finding-mk-average/description/?company_slug=google
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
