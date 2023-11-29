# [The Maze III][title]

There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.

Given the m x n maze, the ball's position ball and the hole's position hole, where ball = [ballrow, ballcol] and hole = [holerow, holecol], return a string instructions of all the instructions that the ball should follow to drop in the hole with the shortest distance possible. If there are multiple valid instructions, return the lexicographically minimum one. If the ball can't drop in the hole, return "impossible".

If there is a way for the ball to drop in the hole, the answer instructions should contain the characters 'u' (i.e., up), 'd' (i.e., down), 'l' (i.e., left), and 'r' (i.e., right).

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

You may assume that the borders of the maze are all walls (see examples).


## Solution
遇到求最短路径， 一定是 BFS， 而不是 DFS， 因为 DFS 需要求出每个可达路径后进行比较才能得到最短路径。
而 BFS 则是逐层遍历，第一个访问到的可达点， 必定是最短路径

```
做 bfs 遍历的模板逻辑是：
通过一个 queue 把起点加进去
如果 queue 不为空进行循环
循环中， 先出队首元素， 找到队首元素附近合规的节点加到队列中。
成功的退出条件是找到了终点。或者失败空队列则退出循环
```

此外自己没想到题目的一点就是， 针对 visited 的记忆， 需要在二维的基础上加上方向成为三维。
为什么会这样呢？ 我尝试跑了二维的代码， 遇到了一个 case 是过不了的。

```
0,1,0,1,S,0,0,0,0,Y,1
0,1,0,1,1,1,0,1,1,0,0
1,Y,0,0,0,0,E,0,Y,0,1
0,Y,Y,1,1,1,0,1,Y,Y,1
1,1,0,Y,Y,1,0,0,0,1,1
0,1,0,0,Y,0,Y,1,0,1,0
0,0,Y,Y,1,0,0,1,1,1,0
```
其中 S 做为起始点， E 作为结束点， Y 为走过的路径，预期答案是： "rdluldrdrurdru"
通过 BFS 遍历过程中， 会发现如果不加方向的话，第三行的结束点因为我之前从右往左走时经过过，就没法再访问了， 就导致了返回了无解的情况。
为了解决这个边界 case 引入了三维数组。一个方向遍历过不影响其他三个方向的遍历。

此外写 dirs 和 path 映射时注意二者要一一对应， 写慢一点。

还有就是起始点如何避免重复访问。通过设置了一个唯一的 4 作为其标识，同理 dirToPath 也加了 0,0 对作为 dummy 节点。
```kotlin
class Solution {
    val dirs = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
        intArrayOf(-1, 0),
        intArrayOf(0, 0)
    )
    var dirToPath = charArrayOf(
        'd', 'l', 'r', 'u'
    )

    fun findShortestWay(maze: Array<IntArray>, ball: IntArray, hole: IntArray): String {
        if (maze.isEmpty()) return ""
        val visited = Array(maze.size) { Array(maze.first().size) { BooleanArray(4) } }
        val queue = ArrayDeque<Node>()
        queue.add(Node(ball[0], ball[1], 4, ""))
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node.x == hole[0] && node.y == hole[1]) {
                return node.path
            }
            bfs(node, maze, visited, queue)
        }
        return "impossible"
    }

    private fun bfs(
        node: Node,
        maze: Array<IntArray>,
        visited: Array<Array<BooleanArray>>,
        queue: ArrayDeque<Node>
    ) {
        val dir = node.dir
        var x = node.x + dirs[dir][0]
        var y = node.y + dirs[dir][1]
        if (dir == 4 || x !in maze.indices || y !in maze.first().indices || maze[x][y] == 1) {
            for (i in 0..<4) {
                if (i == dir) continue
                x = node.x + dirs[i][0]
                y = node.y + dirs[i][1]
                if (x in maze.indices && y in maze.first().indices && maze[x][y] ==0 && !visited[x][y][i]) {
                    visited[x][y][i] = true
                    queue.add(Node(x, y, i, node.path + dirToPath[i]))
                }
            }
        } else if (!visited[x][y][dir]) {
            visited[x][y][dir] = true
            queue.add(node.copy(x = x, y = y))
        }
    }

    data class Node(
        val x: Int,
        val y: Int,
        val dir: Int,
        val path: String
    )
}

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: 
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
