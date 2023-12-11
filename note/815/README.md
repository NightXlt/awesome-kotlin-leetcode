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

通过 hashmap 构建每个 station 和公车线路的图 graph。
队列中入队起始站点，visitedStation 记录访问过的公交车站点， visitedRoutes 记录访问过的公交车线路（Boolean array）。 
visited 都是作为剪枝而存在
每次层序遍历结束一层后， res++。
如果发现当前出栈的站点为目标站点时可以直接返回 res 了， 否则访问包含当前站点出现的线路中， 未访问过的线路中的站点。

此外需要注意的一点是操作 graph 获取 station 时，是可能越界的， 因为输入的 source 可能非法。

```kotlin

class Solution {

    private lateinit var routes: Array<IntArray>
    private var source: Int = 0
    private var target: Int = 0

    /**
     * 单向 bfs 且以 station 作为访问入口， 更易懂且高效
     */
    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        if (routes.isEmpty()) return 0
        if (source == target) return 0
        this.routes = routes
        this.source = source
        this.target = target
        return bfs()
    }

    private fun bfs(): Int {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        val queue = ArrayDeque<Int>()
        val visitedStation = mutableSetOf<Int>()
        val visitedRoutes = BooleanArray(routes.size)
        for (route in routes.indices) {
            for (station in routes[route]) {
                val set = graph.getOrPut(station) { mutableSetOf() }
                set.add(route)
            }
        }
        queue.add(source)
        visitedStation.add(source)
        var res = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val station = queue.removeFirst()
                if (station == target) return res
                // 输入的 source 可能是非法的
                val routesOfCurStation = graph[station] ?: return@repeat
                for (route in routesOfCurStation) {
                    if (visitedRoutes[route]) continue
                    visitedRoutes[route] = true
                    for (nextStation in routes[route]) {
                        if (nextStation in visitedStation) continue
                        visitedStation.add(nextStation)
                        queue.add(nextStation)
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



[title]: https://leetcode.cn/problems/bus-routes/description/
[ajl]: https://github.com/Blankj/awesome-java-leetcode
