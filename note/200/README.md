# [Number of Islands][title]

## Solution
三种解法，深搜 + 广搜 + 并查集，仅记录前两种
遍历数组中每个元素，判断元素值为1时，更新当前节点状态为0，防止死循环；然后开始搜索
搜索分为深搜和广搜。
深搜结束条件： 下标越界或者访问到为0的数字了

广搜使用队列数据结构记录访问下标顺序
结束条件是队列中元素为空。在下标i,j合法且dp\[i\]\[j\]==1才会将下标加入到队列

搜索的核心是找到 
1. 找到递归结束条件
2. 递归方向
3. 避免重复访问，或者可以说是死循环

其他诸如 463.island-perimeter 695.Max Area of Island 均与之类似。 找到核心本质，避免过度陷入题海。
https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
```kotlin
class Solution {
    var row = 0
    var col = 0
    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        var count = 0
        row = grid.size
        col = grid[0].size
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '0') continue
                count++
                grid[i][j] = '0'
                bfsClearNeighborsFlag(i, j, grid)
            }
        }
        return count
    }

    private fun bfsClearNeighborsFlag(i: Int, j: Int, grid: Array<CharArray>) {
        // store pair is also okay
        val neighbors = ArrayDeque<Int>()
        // multiply col rather than row
        neighbors.add(i * col + j)
        while (neighbors.isNotEmpty()) {
            val index = neighbors.pop()
            val iRow = index / col
            val iCol = index % col
            if (iRow - 1 >= 0 && grid[iRow - 1][iCol] == '1') {
                neighbors.add((iRow - 1) * col + iCol)
                grid[iRow - 1][iCol] = '0'
            }
            if (iRow + 1 < row && grid[iRow + 1][iCol] == '1') {
                neighbors.add((iRow + 1) * col + iCol)
                grid[iRow + 1][iCol] = '0'
            }
            if (iCol - 1 >= 0 && grid[iRow][iCol - 1] == '1') {
                neighbors.add(iRow * col + iCol - 1)
                grid[iRow][iCol - 1] = '0'
            }
            if (iCol + 1 < col && grid[iRow][iCol + 1] == '1') {
                neighbors.add(iRow * col + iCol + 1)
                grid[iRow][iCol + 1] = '0'
            }
        }
    }

    fun numIslandsDFS(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        var count = 0
        row = grid.size
        col = grid[0].size
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '0') continue
                count++
                grid[i][j] = '0'
                dfs(grid, i, j)
            }
        }
        return count
    }

    fun dfs(grid: Array<CharArray>, r: Int, c: Int) {
        val nr = grid.size
        val nc: Int = grid[0].size
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return
        }
        grid[r][c] = '0'
        dfs(grid, r - 1, c)
        dfs(grid, r + 1, c)
        dfs(grid, r, c - 1)
        dfs(grid, r, c + 1)
    }
}

```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/number-of-islands/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
