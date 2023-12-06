# [Longest Increasing Path in a Matrix][title]

## Solution
将矩阵中的数字看成图中的节点。由于这个问题是关于递增路径的，因此只关心从较小的数字指向较大的数字的边，两个不同数字在图中对应的节点之间的边是有向边，针对这个问题构建出来的图是一个有向图。同时，由于图中所有边都是从较小的数字指向较大的数字，这样的边不可能形成环，因此构建出来的图一定是有向无环图
接着考虑如何计算图中最长递增路径的长度。由于需要搜索图中的所有节点才能确定最长递增路径的长度，因此这也是一个关于图搜索的问题。解决图搜索通常用广度优先搜索和深度优先搜索这两种不同的方法。这个问题中的路径是非常关键的信息，而深度优先搜索能够很方便地记录搜索的路径，因此深度优先搜索更适合这个问题。
因为不知道从哪个节点开始的递增路径是最长的，所以试着找出从矩阵的每个数字出发的最长递增路径的长度，通过比较可以得出整个矩阵中的最长递增路径的长度

lengths[i][j]”保存的是从矩阵中坐标为（i，j）的数字出发的最长递增路径的长度，然后通过比较得出矩阵中最长的递增路径的长度longest。

如果“lengths[i][j]”的值大于0，就说明之前已经计算过以坐标（i，j）为起点的最长递增路径的长度，如果在计算以其他坐标为起点的最长递增路径的长度时需要以坐标（i，j）为起点的最长递增路径的长度，就没有必要再次计算，只需要直接返回就可以。矩阵lengths在这里还起到了缓存的作用，能够确保以任意坐标为起点的最长递增路径的长度只需要计算一次。

由于总是沿着数字递增的方向（“matrix[r][c]>matrix[i][j]”为true时）在矩阵对应的图中搜索，这相当于是在一个有向无环图中搜索，因此不会出现重复访问一个节点的情况，也无须判断一个节点之前是否访问过。

```kotlin
class Solution {
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0
        val lengths = Array(matrix.size) { IntArray(matrix[0].size) }
        var res = Int.MIN_VALUE
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                val length = dfs(matrix, i, j, lengths)
                res = max(res, length)
            }
        }
        return res
    }

    private fun dfs(
        matrix: Array<IntArray>,
        i: Int, j: Int,
        lengths: Array<IntArray>
    ): Int {
        if (lengths[i][j] > 0) return lengths[i][j]
        val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, -1))
        var length = 1
        for (dir in dirs) {
            val row = i + dir[0]
            val col = j + dir[1]
            if (row in matrix.indices && col in matrix[0].indices
                && matrix[row][col] > matrix[i][j]) {
                val path = dfs(matrix, row, col, lengths)
                length = max(path + 1, length)
            }
        }
        lengths[i][j] = length
        return lengths[i][j]
    }
}

```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
