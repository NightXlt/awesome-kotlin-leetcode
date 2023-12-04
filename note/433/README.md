# [Minimum Genetic Mutation][title]

## Description

A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.

**Example 1:**
```text
Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1
```



## 思路
因为是希望找到最短的变换距离, 而不求所有的变换方式, bfs 会更加合适

我们分别把 bank 的字符串计入 set 中, 方便后续搜索过程中查找
我们需要一个 visited 记录访问过的节点用来剪枝.
针对队列中每个访问的字符串, 因为其最大 size 为 8, 我们遍历字符串的每个字符, 尝试进行更换为其他合理字符.
加入队列前, 判断是否访问过, 以及是否在合理字符串中, 如果满足则加入队列.

此外 count 统计过程是以每一层广度遍历结束作为节点, 然后才能加 1, 此外就是变换结束如果等于 end, 说明我们找到了递归结束节.
返回结果前记得 +1 呀!

```kotlin
class Solution {
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        val validGene = bank.toSet()
        if (endGene !in validGene) {
            return -1
        }
        if (startGene == endGene) {
            return 0
        }
        val visited = mutableSetOf<String>()
        val queue = ArrayDeque<String>()
        queue.add(startGene)
        visited.add(startGene)
        val geneKinds = charArrayOf('A', 'C', 'G', 'T')
        var res = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val cur = queue.removeFirst()
                for (i in 0..<8) {
                    val builder = StringBuilder(cur)
                    for (j in 0..<4) {
                        if (cur[i] == geneKinds[j]) continue
                        builder[i] = geneKinds[j]
                        val str = builder.toString()
                        if (str == endGene) {
                            res++
                            return res
                        }
                        if (str !in visited && str in validGene) {
                            queue.add(str)
                            visited.add(str)
                        }
                    }
                }
            }
            res++
        }
        return -1
    }
}


```


## 结语

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/minimum-genetic-mutation/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
