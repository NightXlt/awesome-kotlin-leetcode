# [Game of Life][title]

## Solution
因为数组元素只有 0，1 两位， 那可以通过二进制的第二位来记录下一轮当前的存活状态，不能直接在原数组的第一位上更改状态， 这会影响到后续统计， 因此才考虑记录到了第二位
这样的话，判断周围是否存活时， 可以通过 and 1 == 1 判断先前是否存活。
此外就是后续二维遍历时， 数组元素整体右移一位就可以了

```kotlin
class Solution {
    fun gameOfLife(board: Array<IntArray>) {
        if (board.isEmpty()) return
        for (i in board.indices) {
            for (j in board.first().indices) {
                val num = getLiveCell(board, i, j)
                if ((board[i][j] == 1 && num in 2..3) || (board[i][j] == 0 && num == 3)) {
                    board[i][j] = board[i][j] or (1 shl 1)
                }
            }
        }
        for (i in board.indices) {
            for (j in board.first().indices) {
                board[i][j] = board[i][j] shr 1
            }
        }
    }

    val dir = intArrayOf(0, 1, -1)
    private fun getLiveCell(board: Array<IntArray>, x: Int, y: Int): Int {
        var num = 0
        val invalidPair = 0 to 0
        for (i in dir) {
            for (j in dir) {
                if ((i to j) == invalidPair) continue
                val row = x + i
                val col = y + j
                if (row in board.indices && col in board.first().indices && (board[row][col] and 1) == 1) {
                    num++
                }
            }
        }
        return num
    }
}
```

## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/game-of-life/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
