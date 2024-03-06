# [Pacific Atlantic Water Flow][title]

## Solution
因为边界的单元格可以直接流入到海洋中， 可以分别以左边界和上边界进行深搜计算可能流入到太平洋的内部单元格， 右边界和下边界进行深搜计算可能流入到大西洋的单元格。
因为是反向遍历，原先从内部遍历是 <= 的情况， 我们从边界逆向深搜时要改为 >=。

```kotlin
class Solution {

    lateinit var heights: Array<IntArray>
    
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        if (heights.isEmpty()) return emptyList()
        this.heights = heights
        val result = mutableListOf<List<Int>>()
        val m = heights.size
        val n = heights.first().size
        val pacific = Array(m) { BooleanArray(n) }
        val atlantic = Array(m) { BooleanArray(n) }
        for (i in heights.indices) {
            dfs(i, 0, pacific)
            dfs(i, n - 1, atlantic)
        }
        for (i in heights.first().indices) {
            dfs(0, i, pacific)
            dfs(m - 1, i, atlantic)
        }
        for (i in heights.indices) {
            for (j in heights.first().indices) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(listOf(i, j))
                }
            }
        }
        return result
    }

    private val dirs = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
        intArrayOf(1, 0),
    )

    private fun dfs(row: Int, col: Int, ocean: Array<BooleanArray>) {
        if (ocean[row][col]) return
        ocean[row][col] = true
        for ((rowDiff, colDiff) in dirs) {
            val newRow = row + rowDiff
            val newCol = col + colDiff
            if (newRow in ocean.indices && newCol in ocean.first().indices && heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, ocean)
            }
        }
    }
}

```



## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode.cn/problems/pacific-atlantic-water-flow/description/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
