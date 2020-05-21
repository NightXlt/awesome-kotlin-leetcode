# [Minesweeper][title]

## Solution
扫雷，玩的菜的一匹，题目都读不懂。
题眼：如果一个空白块附近没有地雷，需要递归地翻开它周围的地雷；明确解法方向是dfs,而且深搜过程中仅访问‘E’节点。

1. 深搜前排除‘M’
2. 深搜中如果四周有地雷，统计其 count,该方块数字设置为 count，结束深搜
3. 四周没有地雷，将board[row]\[col] = 'B', 继续递归遍历四周的'E'方块。  

```kotlin
class Solution {
    var m = 0
    var n = 0

    fun updateBoard(board: Array<CharArray>, click: IntArray): Array<CharArray> {
        if (board.isEmpty() || click.size < 2) return board
        val row = click[0]
        val col = click[1]
        if (board[row][col] == 'M') {
            board[row][col] = 'X'
            return board
        }
        m = board.size
        n = board[row].size
        dfs(row, col, board)
        return board
    }

    private fun dfs(row: Int, col: Int, board: Array<CharArray>) {
        if (row !in 0 until m || col !in 0 until n || board[row][col] != 'E') return
        var count = 0
        for (dir in dirs) {
            val x = row + dir[0]
            val y = col + dir[1]
            if (x in 0 until m && y in 0 until n && board[x][y] == 'M') count++
        }
        if (count != 0) {
            board[row][col] = '0' + count
            return
        }
        board[row][col] = 'B'
        for (dir in dirs) {
            val x = row + dir[0]
            val y = col + dir[1]
            dfs(x, y, board)
        }
    }

    companion object {
        @JvmField
        val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(-1, 1), intArrayOf(1, -1))
    }
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/minesweeper/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
