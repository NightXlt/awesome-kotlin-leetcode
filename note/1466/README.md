# [Reorder Routes to Make All Paths Lead to the City Zero][title]

## Solution
参考了这个[题解](https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/solution/ru-guo-ke-yi-zai-gei-wo-yi-ge-bfsde-ji-hui-by-suri/)，
通过 BFS遍历邻接表 访问图。通过 map<node, HashSet<Node>> 实现邻接表记录。为了实现无向邻接表，将 HashSet 中的节点 取负值表示逆向。
如 connections 中有 1，2.先顺序记录为 \[1, \[2]], 逆序记录为\[2, \[-1]]. 之后遍历图时根据正数节点数目统计需要变化边的数目

遍历时需要一个 bool 数组标识已访问过的节点避免重复访问,  通过 queue 实现 BFS。

```kotlin
import kotlin.math.abs
import kotlin.collections.HashMap
import kotlin.collections.HashSet
class Solution {
    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        if (connections.isEmpty()) return 0
        val map = HashMap<Int, HashSet<Int>>()
        for (connection in connections) {
            val adjacencyList = map.getOrDefault(connection[0], HashSet())
            adjacencyList.add(connection[1])
            map[connection[0]] = adjacencyList
            val reverseAdjacencyList = map.getOrDefault(connection[1], HashSet())
            reverseAdjacencyList.add(-connection[0])
            map[connection[1]] = reverseAdjacencyList
        }
        return dfs(map, n)
    }

    private fun dfs(map: HashMap<Int, HashSet<Int>>, n: Int): Int {
        val flag = BooleanArray(n)
        val queue = LinkedList<Int>()
        queue.add(0)
        flag[0] = true
        var res = 0
        while (queue.isNotEmpty()) {
            val adjacencyList = map[queue.poll()] ?: return 0
            for (i in adjacencyList) {
                val absI = abs(i)
                if (flag[absI]) continue
                if (i > 0) res++ // 逆序统计节点在这里就可以用上了
                queue.add(absI)
                flag[absI] = true
            }
        }
        return res
    }
}
```
## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
