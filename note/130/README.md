# [Surrounded Regions][title]

## Solution
这道深搜题有点意思的是把所有被围绕的 O 换成 X. 我困在了从里面往外深搜的逻辑中，没想明白。
看了眼题解就懂了，我们从边缘出发。首行， 首列， 最后一行，一列的 O 开始深搜，深搜到的改为 S, 

后面遍历数组元素 把所有没有被访问的 O 改为 X. 而 S 改回为 O, 有点逆向思考的意思。

```kotlin
class Solution {
    fun solve(board: Array<CharArray>) {
        if (board.isEmpty()) return
        for (i in board.indices) {
            dfs(board, i, 0)
            dfs(board, i, board.first().lastIndex)
        }
        for (i in board.first().indices) {
            dfs(board, 0, i)
            dfs(board, board.lastIndex, i)
        }
        for (i in board.indices) {
            for (j in board.first().indices) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'
                } else if (board[i][j] == 'S') {
                    board[i][j] = 'O'
                }
            }
        }
    }

    val dirs = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
    )

    private fun dfs(board: Array<CharArray>, x: Int, y: Int) {
        if (x !in board.indices || y !in board.first().indices || board[x][y] != 'O') {
            return
        }
        board[x][y] = 'S'
        for ((rowDiff, colDiff) in dirs) {
            val nextX = x + rowDiff
            val nextY = y + colDiff
            dfs(board, nextX, nextY)
        }
    }
}
```

## Conclusion
如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]

[title]: https://leetcode.cn/problems/surrounded-regions/description/?envType=study-plan-v2&envId=top-interview-150
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
