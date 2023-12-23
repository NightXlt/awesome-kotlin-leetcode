# [Count Unreachable Pairs of Nodes in an Undirected Graph][title]

## Solution
第一看到图时， 想到用并查集来解吧， 但发现光用并查集的基础版还搞不定， 会超时在最后双重遍历连通图哪里， O(n^2) 的复杂度，因为试图用 * 法求当前连通图和其他连通图之间的 pairs.

如果这样会超时的话，用一个额外的数组记录每个连通分量的大小， 同时加上路径压缩， 降低并查集高度。

最后统计 pairs 时， 遍历每个节点， 累加 n - 每个节点归属连通分量的大小， 最后结果需要 / 2 是因为存在重复的情况， 比如 （1，2） 和 （2，1）是算同一个的

```kotlin
    class Solution {
    fun countPairs(n: Int, edges: Array<IntArray>): Long {
        if (edges.isEmpty()) return (n.toLong() * (n -1)) / 2L
        val find = IntArray(n) { it }
        val sizes = IntArray(n) { 1 }
        for (edge in edges) {
            val (first, second) = edge
            merge(find, first, second, sizes)
        }
        var res = 0L
        for (i in sizes.indices) {
            res += n - sizes[find(find, i)]
        }
        return res / 2
    }

    fun merge(find: IntArray, x: Int, y: Int, sizes: IntArray) {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx == fy) return
        // 如果 x’ size > y’s size, 那么应该是把 y 合到 x 上面， 因为 x, y 到根的距离都是扁平的，那么子节点越多的层级越低访问代价越低
        if (sizes[fx] >= sizes[fy]) {
            find[fy] = fx
            sizes[fx] += sizes[fy]
        } else {
            find[fx] = fy
            sizes[fy] += sizes[fx]
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var root = x
        while (root != find[root]) {
            root = find[root]
        }
        // 路径压缩
        find[x] = root
        return root
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
