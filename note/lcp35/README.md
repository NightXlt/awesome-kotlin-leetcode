# [电动车游城市][title]

## Solution
首先建图， 存储每个城市相邻的城市和距离

使用一个二维数组保存结果 res[i][j] = k， i = 所在城市， j = 剩余电量， k = start 到 i 的最短时间
二维数组还可以用来剪枝， 如果耗时比当前节点的最少耗时还长， 就没必要访问了。

用队列来记录每个路径的信息 {time,cur,power}， time = start 到 cur 已用时间， cur = 所在城市， power = 剩余电量
(使用优先队列来保证每次优先执行已用时间最少的路径)

每次只会有两种操作

充一次电 - 新时间 = 已用时间 + 当前城市每单位充电需要时间， 新电量 = 剩余电量 + 1
去往下一个城市 - 新时间 = 已用时间 + 去往该需要消耗的时间， 新电量 = 剩余电量 − 去往该城市需要消耗的电量

递归节点结： 
当前访问节点为 end.

```kotlin
class Solution {
    fun electricCarPlan(paths: Array<IntArray>, cnt: Int, start: Int, end: Int, charge: IntArray): Int {
        val n = charge.size
        val graph = mutableMapOf<Int, MutableList<IntArray>>()
        repeat(n) {
            graph[it] = mutableListOf()
        }
        for (edge in paths) {
            val (x, y, distance) = edge
            graph[x]?.add(intArrayOf(y, distance))
            graph[y]?.add(intArrayOf(x, distance))
        }
        val res = Array(n) { IntArray(cnt + 1) { Int.MAX_VALUE / 2 } }
        res[start][0] = 0
        // 优先遍历 time 最少的地点
        val queue = PriorityQueue<IntArray>(Comparator.comparingInt {
            it[1]
        })
        queue.add(intArrayOf(start, 0, 0))
        while (queue.isNotEmpty()) {
            // cur: 当前节点  time: 已用时间  power：剩余电量
            val (cur, time, power) = queue.poll()
            if (time > res[cur][power]) continue
            if (cur == end) return time
            if (power < cnt) {
                val t = time + charge[cur]
                // 如果充 1 单位电后还比当前节点耗时短， 则加入队列
                if (t < res[cur][power + 1]) {
                    res[cur][power + 1] = t
                    queue.add(intArrayOf(cur, t, power + 1))
                }
            }
            for (next in graph.getValue(cur)) {
                val (nextNode, distance) = next
                val t = time + distance
                val p = power - distance
                if (p >= 0 && t < res[nextNode][p]) {
                    res[nextNode][p] = t
                    queue.add(intArrayOf(nextNode, t, p))
                }
            }
        }
        return -1
    }
}


```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/redundant-connection-ii/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
