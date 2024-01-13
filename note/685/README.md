# [Redundant Connection II][title]

## Solution
树中的每个节点都有一个父节点，除了根节点没有父节点。在多了一条附加的边之后，可能有以下两种情况：

附加的边指向根节点，则包括根节点在内的每个节点都有一个父节点，此时图中一定有环路；

附加的边指向非根节点，则恰好有一个节点（即被附加的边指向的节点）有两个父节点，此时图中可能有环路也可能没有环路。

要找到附加的边，需要遍历图中的所有的边构建出一棵树，在构建树的过程中寻找导致冲突（即导致一个节点有两个父节点）的边以及导致环路出现的边。

具体做法是，使用数组 parent 记录每个节点的父节点，初始时对于任何 1≤i≤n 都有 parent[i]=i，

另外创建并查集，初始时并查集中的每个节点都是一个连通分支，该连通分支的根节点就是该节点本身。遍历每条边的过程中，维护导致冲突的边和导致环路出现的边，由于只有一条附加的边，因此最多有一条导致冲突的边和一条导致环路出现的边。

当访问到边 [u,v] 时，进行如下操作：

如果此时已经有 parent[v]≠v，说明 v 有两个父节点，将当前的边 [u,v] 记为导致冲突的边；

否则，令 parent[v]=u，然后在并查集中分别找到 u 和 v 的祖先（即各自的连通分支中的根节点），如果祖先相同，说明这条边导致环路出现，将当前的边 [u,v] 记为导致环路出现的边，如果祖先不同，则在并查集中将 u 和 v 进行合并。

根据上述操作，同一条边不可能同时被记为导致冲突的边和导致环路出现的边。如果访问到的边确实同时导致冲突和环路出现，则这条边被记为导致冲突的边。

以这个 case 为例子来说明这句话：
```text
edges = [[3,1],[2,1],[4,2],[1,4]]
edges = [[2,1],[3,1],[4,2],[1,4]]
```
这个 case 有意思的是, 尽管只调换了前两个数组前后顺序， 但代码逻辑执行链路是不一样的。
因为我们把 [3，1] 提到了前面， 在代码中，我们合并了 3，1 到并查集中， 后面的 2，1 导致了冲突判断为冲突，跳过循环， 没有走并查集合并， 这就导致最后添加 （1，4） 时， 发现其没有成环， 尽管我们绘图出来确实成环了， 这时我们认为导致冲突的 (2,1) 是冲突的边也是没问题的。
这大抵是为啥访问到的边导致成环和冲突时， 我们认为其是冲突的原因吧。

```kotlin
class Solution {
    fun findRedundantDirectedConnection(edges: Array<IntArray>): IntArray {
        val n = edges.size
        val find = IntArray(n + 1) { it } // 并查集用来判断是否成环
        // parent 简单判断入度是否 > 2, 同时记录了如果成环点的始点
        val parent = IntArray(n + 1) { it }
        // record inDegree==2 index
        var conflict = -1
        // record cycle index
        var cycle = -1
        for (i in 0 until n) {
            val (node1, node2) = edges[i]
            if (parent[node2] != node2) {
                conflict = i
            } else {
                parent[node2] = node1
                if (find(find, node1) == find(find, node2)) {
                    cycle = i
                } else {
                    merge(find, node1, node2)
                }
            }
        }
        // 不存在入度 > 2 的情况，必定是成环指向了根节点
        return if (conflict < 0) { // 指向根节点
            intArrayOf(edges[cycle][0], edges[cycle][1])
        } else { // 入度 > 2 的情况
            val conflictEdge = edges[conflict]
            if (cycle >= 0) { // 如果同时也成环了， 获取冲突边终点的父亲
                intArrayOf(parent[conflictEdge[1]], conflictEdge[1])
            } else {
                intArrayOf(conflictEdge[0], conflictEdge[1])
            }
        }
    }

    private fun merge(find: IntArray, x: Int, y: Int) {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var index = x
        while (find[index] != index) {
            index = find[index]
        }
        return index
    }
}

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/redundant-connection-ii/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
