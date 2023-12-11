# [Bus Routes][title]

## Description
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

**Example 1:**
```text
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
```

**Example 2:**
```text
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
```

## 思路
我们令每个公交站为一个「车站」，由一个「车站」可以进入一条或多条「路线」。

问题为从「起点车站」到「终点车站」，所进入的最少路线为多少。

抽象每个「路线」为一个点，当不同「路线」之间存在「公共车站」则为其增加一条边权为 1 的无向边。

那么点和点之间可以构成一个图， 解决本题其实是在尝试找到从始发点到终点的最短路径。图中的最短路径首先想到 BFS.


将「起点」和「终点」所能进入的路线分别放入两个方向的队列，如果「遇到公共的路线」或者「当前路线包含了目标位置」，说明找到了最短路径。

另外，双向 BFS 在无解的情况下不如单向 BFS。因此我们可以先使用「并查集」进行预处理，判断「起点」和「终点」是否连通，如果不联通，直接返回 −1，有解才调用双向 BFS。

由于使用「并查集」预处理的复杂度与建图是近似的，增加这样的预处理并不会越过我们时空复杂度的上限，因此这样的预处理是有益的。一定程度上可以最大化双向 BFS 减少搜索空间的效益。


```kotlin

class Solution {
    private var find = IntArray(N.toInt())
    private lateinit var routes: Array<IntArray>
    private var source: Int = 0
    private var target: Int = 0
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if (routes.isEmpty()) return 0
        if (source == target) return 0
        this.routes = routes
        this.source = source
        this.target = target
        find.indices.forEach { find[it] = it }
        for (route in routes) {
            for (index in route) {
                merge(find, index, route[0])
            }
        }
        if (find(find, source) != find(find, target)) {
            return -1
        }
        return doubleBfs()
    }

    // graph of station and lines number mapping
    private val graph = mutableMapOf<Int, MutableSet<Int>>()
    private fun doubleBfs(): Int {
        val m1 = mutableMapOf<Int, Int>()
        val m2 = mutableMapOf<Int, Int>()
        val d1 = ArrayDeque<Int>()
        val d2 = ArrayDeque<Int>()
        for ((i, route) in routes.withIndex()) {
            for (station in route) {
                // 将从起点可以进入的路线加入正向队列
                if (station == source) {
                    m1[i] = 1
                    d1.add(i)
                }
                // 将从终点可以进入的路线加入反向队列
                if (station == target) {
                    m2[i] = 1
                    d2.add(i)
                }
                val set = graph.getOrPut(station) { mutableSetOf() }
                set.add(i)
            }
        }
        val s1 = graph.getValue(source)
        val s2 = graph.getValue(target)
        val total = s1.toMutableSet()
        total.retainAll(s2)
        // has intersection means in the same line
        if (total.isNotEmpty()) return 1
        while (d1.isNotEmpty() && d2.isNotEmpty()) {
            val res = if (d1.size < d2.size) {
                update(d1, m1, m2)
            } else {
                update(d2, m2, m1)
            }
            if (res != -1) return res
        }
        return -1
    }

    private fun update(d: ArrayDeque<Int>, cur: MutableMap<Int, Int>, other: MutableMap<Int, Int>): Int {
        val size = d.size
        repeat(size) {
            // 取出当前所在的路线，与进入该路线所花费的距离
            val curLine = d.removeFirst()
            val step = cur.getValue(curLine)
            // 遍历该路线所包含的车站
            for (station in routes[curLine]) {
                // 遍历将由该线路的车站发起的路线
                val lines = graph[station] ?: continue
                for (line in lines) {
                    if (cur.containsKey(line)) continue
                    if (other.containsKey(line)) return step + other.getValue(line)
                    cur[line] = step + 1
                    d.add(line)
                }
            }
        }
        return -1
    }

    private fun merge(find: IntArray, x: Int, y: Int) {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
            find[x] = fy
        }
    }

    private fun find(find: IntArray, x: Int): Int {
        var temp = x
        while (find[temp] != temp) {
            temp = find[temp]
        }
        return temp
    }

    companion object {
        private const val N = 1e6 + 10
    }
}

```




## 结语
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-java-leetcode][ajl]



[title]: https://leetcode.cn/problems/bus-routes/description/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
