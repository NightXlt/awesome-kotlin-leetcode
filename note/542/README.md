# [01 Matrix][title]

## Solution
这个题目要求计算每个格子离最近的0的距离。根据题目的要求，上、下、左、右相邻的两个格子的距离为1。

可以将图看成一个无权图，图中两个节点的距离是连通它们的路径经过的边的数目。由于这个问题与无权图的最近距离相关，因此可以考虑应用广度优先搜索解决。

广度优先搜索需要一个队列。这个问题是求每个格子离最近的0的距离，因此可以将所有的0当作初始节点添加到队列中，然后以值为0的节点作为起点做广度优先搜索。

如果经过d步到达某个格子，那么该格子离最近的0的距离就是d。

用一个二维数组来记录每个格子离最近的0的距离。如果matrix[i][j]为0，那么这个格子离最近的0的距离自然是0，因此dists[i][j]设为0。如果matrix[i][j]的值为1，则先用最大的整数值初始化dists[i][j]，接下来搜索到对应的节点时再更新它的值。

每次从队列中取出一个坐标为pos的格子，该格子离最近的0的距离用变量dist表示。从该格子出发沿着上、下、左、右到达坐标为（r，c）的格子。如果该格子之前没有到达过，此时“dists[r][c]”的值仍然为最大的整数值，那么“dists[r][c]>dist+1”的值为true。

由于是从离最近的0的距离为dist的格子多走一步到达该格子的，因此该格子离最近的0的距离是dist+1。此外，还需要将该格子添加到队列中，以便接下来搜索与该格子相连的其他节点。
```kotlin
class Solution {
    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val rows = mat.size
        val cols = mat[0].size
        val dists = Array(rows) { IntArray(cols) { 0 } }
        val queue = ArrayDeque<IntArray>()
        repeat(rows) { i ->
            repeat(cols) { j ->
                if (mat[i][j] == 0) {
                    queue.add(intArrayOf(i, j))
                    dists[i][j] = 0
                } else {
                    dists[i][j] = Int.MAX_VALUE
                }
            }
        }
        val dirs = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
        )
        while (queue.isNotEmpty()) {
            val pos = queue.removeFirst()
            val dist = dists[pos[0]][pos[1]]
            for (dir in dirs) {
                val r = pos[0] + dir[0]
                val c = pos[1] + dir[1]
                if (r in mat.indices && c in mat[0].indices) {
                // 如果访问到非 0 节点统计其步调， 并记录最小值，然后加入到队列中
                    if (dists[r][c] > dist + 1) {
                        dists[r][c] = dist + 1
                        queue.add(intArrayOf(r, c))
                    }
                }
            }
        }
        return dists
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/01-matrix/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
