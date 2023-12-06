# [Course Schedule II][title]

## Solution
可行的修课序列实际上是图的拓扑排序序列。图中的每条边都是从先修课程指向后修课程，而拓扑排序能够保证任意一条边的起始节点一定排在终止节点的前面，因此拓扑排序得到的序列与先修顺序一定不会存在冲突，于是这个问题转变成如何求有向图的拓扑排序序列。

对有向图进行拓扑排序的算法是每次找出一个入度为0的节点添加到序列中，然后删除该节点及所有以该节点为起点的边。重复这个过程，直到图为空或图中不存在入度为0的节点。


```kotlin
class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        if (numCourses <= 0) return intArrayOf()
        val inDegree = IntArray(numCourses)
        for (prerequisite in prerequisites) {
            inDegree[prerequisite[0]]++
        }
        val queue: Queue<Int> = ArrayDeque()
        for ((index, i) in inDegree.withIndex()) {
            if (i == 0) queue.offer(index)
        }
        val res = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val curElement = queue.poll()
            res.add(curElement)
            for (prerequisite in prerequisites) {
                if (prerequisite[1] == curElement) {
                    inDegree[prerequisite[0]]--
                    if (inDegree[prerequisite[0]] == 0) queue.add(prerequisite[0])
                }
            }
        }
        if (res.size != numCourses) return intArrayOf()
        return res.toIntArray()
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/course-schedule-ii/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
