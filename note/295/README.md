# [Find Median from Data Stream][title]

## Solution
两种解法：

### 插入排序
通过插入排序维持一个有序数组，取时取中间数字即可

时间复杂度：
addNum: O(n)
findMedian: O(1)

空间复杂度：O(n)
```kotlin
class MedianFinder() {

    /** initialize your data structure here. */

    private val data = mutableListOf<Int>()

    fun addNum(num: Int) {
        if (data.isEmpty()) {
            data.add(num)
            return
        }
        data.add(Int.MIN_VALUE)
        var i = data.lastIndex
        while (i > 0) {
            if (num < data[i - 1]) {
                data[i] = data[i - 1]
            } else {
                break
            }
            i--
        }
        data[i] = num
    }

    fun findMedian(): Double {
        val size = data.size
        val m = size / 2
        return if (size % 2 == 0) (data[m] + data[m - 1]) / 2.0 else data[m].toDouble()
    }

}

```

### 堆
用两个堆记录数组的两半，这样通过取其中一个堆顶部元素 或者两个堆的顶部元素的均值得到中位数。
那么对于左半边元素使用最大堆记录最右侧的值， 右半边元素使用最小堆记录最左侧值。
保证最大堆和最小堆数量差不超过一。
为了能够区分奇数的情况， 奇数时保证左半边元素始终比右半边元素多一个。
时间复杂度：
addNum: O(logn)
findMedian: O(1)

空间复杂度：O(n)
```kotlin
class MedianFinderHeap() {

    /** initialize your data structure here. */

    private val maxHeap = PriorityQueue<Int>() { a, b -> b - a }
    private val minHeap = PriorityQueue<Int>()

    fun addNum(num: Int) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num)
            return
        }
        if (num <= maxHeap.peek()) {
            maxHeap.add(num)
        } else {
            minHeap.add(num)
        }
        // 保证最大堆和最小堆数量差不超过一。防止递减序列
        if (maxHeap.size > minHeap.size + 1) {
            minHeap.add(maxHeap.remove())
        }
        // 为了能够区分奇数的情况， 奇数时保证左半边元素始终比右半边元素多一个。
        if (minHeap.size > maxHeap.size) {
            maxHeap.add(minHeap.remove())
        }
    }

    fun findMedian(): Double {
        if (maxHeap.size > minHeap.size) {
            return maxHeap.peek().toDouble()
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0
    }

}

```


## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/find-median-from-data-stream/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
