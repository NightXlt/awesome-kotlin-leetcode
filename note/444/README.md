# [Sequence Reconstruction][title]

You are given an integer array nums of length n where nums is a permutation of the integers in the range [1, n]. You are also given a 2D integer array sequences where sequences[i] is a subsequence of nums.

Check if nums is the shortest possible and the only supersequence. The shortest supersequence is a sequence with the shortest length and has all sequences[i] as subsequences. There could be multiple valid supersequences for the given array sequences.

For example, for sequences = [[1,2],[1,3]], there are two shortest supersequences, [1,2,3] and [1,3,2].
While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence possible is [1,2,3]. [1,2,3,4] is a possible supersequence but not the shortest.
Return true if nums is the only shortest supersequence for sequences, or false otherwise.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

Example 1:
```text
Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
Output: false
Explanation: There are two possible supersequences: [1,2,3] and [1,3,2].
The sequence [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
The sequence [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
Since nums is not the only shortest supersequence, we return false.
```

Example 2:
```text
Input: nums = [1,2,3], sequences = [[1,2]]
Output: false
Explanation: The shortest possible supersequence is [1,2].
The sequence [1,2] is a subsequence of it: [1,2].
Since nums is not the shortest supersequence, we return false.
```

Example 3:
```text
Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
Output: true
Explanation: The shortest possible supersequence is [1,2,3].
The sequence [1,2] is a subsequence of it: [1,2,3].
The sequence [1,3] is a subsequence of it: [1,2,3].
The sequence [2,3] is a subsequence of it: [1,2,3].
Since nums is the only shortest supersequence, we return true.
```

## Solution
按照题目的要求，如果在seqs的某个序列中数字i出现在数字j的前面，那么由seqs重建的序列中数字i一定也要出现在数字j的前面。

也就是说，重建序列的数字顺序由seqs的所有序列定义。可以将seqs中每个序列的每个数字看成图中的一个节点，两个相邻的数字之间有一条从前面数字指向后面数字的边。

如果得到的是有向图的拓扑排序序列，那么任意一条边的起始节点在拓扑排序序列中一定位于终止节点的前面。因此，由seqs重建的序列就是由seqs构建的有向图的拓扑排序的序列。这个问题就转变成判断一个有向图的拓扑排序序列是否唯一。

队列queue中保存的是入度为0的节点。每次从队列中取出一个节点添加到拓扑排序序列中，然后将所有与该节点相邻的节点的入度减1（相当于删除所有以该节点为起始节点的边），如果发现有新的入度为0的节点则添加到队列之中。

由于目标是判断图的拓扑排序序列是否唯一，而当某个时刻队列中的节点数目大于1时，就知道此时有多个入度为0的节点，那么按任意顺序排列这个入度为0的节点都能生成有效的拓扑排序序列，因此拓扑排序的序列不是唯一的。由此可知，上述代码只在队列的大小为1的时候重复添加入度为0的节点。

```kotlin
class Solution {
    fun sequenceReconstruction(nums: IntArray, sequences: List<List<Int>>): Boolean {
        if (nums.isEmpty() && sequences.isEmpty()) return true
        if (nums.isEmpty() || sequences.isEmpty()) return false
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        val inDegrees = mutableMapOf<Int, Int>()
        for (seq in sequences) {
            for (num in seq) {
                if (num < 1 || num > nums.size) {
                    return false
                }
                graph.putIfAbsent(num, mutableSetOf())
                inDegrees.putIfAbsent(num, 0)
            }
            constructDirectedGraph(seq, graph, inDegrees)
        }
        val queue = ArrayDeque<Int>()
        queue.addAll(inDegrees.keys.filter { inDegrees[it] == 0 })
        val res = mutableListOf<Int>()
        while (queue.size == 1) {
            val num = queue.removeFirst()
            res.add(num)
            for (next in graph.getValue(num)) {
                inDegrees[next] = inDegrees[next]!! - 1
                if (inDegrees[next] == 0) {
                    queue.add(next)
                }
            }
        }
        return res.toIntArray().contentEquals(nums)
    }

    private fun constructDirectedGraph(
        seq: List<Int>,
        graph: MutableMap<Int, MutableSet<Int>>,
        inDegrees: MutableMap<Int, Int>
    ) {
        for (i in 1 until seq.size) {
            val num1 = seq[i - 1]
            val num2 = seq[i]
            if (!graph.getValue(num1).contains(num2)) {
                graph.getValue(num1).add(num2)
                inDegrees[num2] = inDegrees[num2]!! + 1
            }
        }
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/sequence-reconstruction/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
