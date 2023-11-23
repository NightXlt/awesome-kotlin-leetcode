# [Find K Pairs with Smallest Sums][title]


## 思路
因为包含 k 个最小对的和， 首选想到通过堆来维护最大堆维护最小的 k 个对。
如果遇到比堆顶元素更小的元素时， 则出队顶元素， 否则则忽略。
此外在队列大小小于 K 时， 可以直接进队。
想获得最小的元素序列， 得用最大堆

Time complexity: O(k^2logk)
Space complexity: O(K)
```kotlin
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        if (k < 0) error("Illegal input: k($k)")
        // max heap
        val queue = PriorityQueue<Pair<Int, Int>> { p1, p2 ->
            p2.sum() - p1.sum()
        }
        for (i in 0..<min(k, nums1.size)) {
            for (j in 0..<min(k, nums2.size)) {
                if (queue.size < k) {
                    queue.offer(nums1[i] to nums2[j])
                } else {
                    val peekSum = queue.peek().sum()
                    if (peekSum > nums1[i] + nums2[j]) {
                        queue.poll()
                        queue.offer(nums1[i] to nums2[j])
                    }
                }
            }
        }
        return queue.map { it.toList() }.toList()
    }
fun Pair<Int, Int>.sum(): Int {
    return first + second
}
```


还有一种更优的解法：
通过维护最小堆，堆中记录了索引，先记录 nums1 前 k 个下标和 nums2 第一个的下标的映射
堆得排序按照 nums1[index1_1] + nums2[index1_2] - (nums1[index2_1] + nums2[index2_2] )
然后开始统计 k 个最小对时， 首先出堆顶元素必定是最小的序列和，但下一个元素不一定在堆顶
因为我们没有将 （0，1）的下标加入到堆顶，我们需要追加到堆顶中综合比较 （1，0） 和 （0，1）的大小
其中 0,1 分别表示 nums1, nums2 的下标。
这样优化后
TC: O(klogk)
SC: O(k)
```kotlin

    fun kSmallestPairsWithMinHeap(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        if (k < 0) error("Illegal input: k($k)")
        if (nums1.isEmpty() || nums2.isEmpty()) return emptyList()
        val minHeap = PriorityQueue<Pair<Int, Int>> { p1, p2 ->
            nums1[p1.first] + nums2[p1.second] - nums1[p2.first] - nums2[p2.second]
        }
        // min heap
        for (i in 0..<min(k, nums1.size)) {
            minHeap.offer(i to 0)
        }
        var k = k
        val res = mutableListOf<List<Int>>()
        while (k > 0 && minHeap.isNotEmpty()) {
            val indexs = minHeap.poll()
            res.add(listOf(nums1[indexs.first], nums2[indexs.second]))
            if (indexs.second < nums2.lastIndex) {
                minHeap.offer(indexs.first to (indexs.second + 1))
            }
            k--
        }
        return res
    }

```
## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/description/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
