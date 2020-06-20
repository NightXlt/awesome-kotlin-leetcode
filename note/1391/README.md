# [check-if-there-is-a-valid-path-in-a-grid][title]

## Solution
两个点
1. 使用并查集将联通的边合并起来
2. 判断边和边是联通的

由于并查集是一维的所以在使用时我们需要对其进行降维处理。通过 x * n + y转换为一维数组
判断边和边联通就比较暴力了，根据这条边的序号枚举能够联通的其余三条边。

```kotlin
package com.blankj.medium._1391

class Solution {
    lateinit var set: DisjointSet
    var m = 0
    var n = 0
    lateinit var grid: Array<IntArray>
    fun hasValidPath(grid: Array<IntArray>): Boolean {
        if (grid.isEmpty()) return true
        set = DisjointSet()
        m = grid.size
        n = grid[0].size
        this.grid = grid
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                handle(i, j)
            }
        }
        return set.find(getNum(0, 0)) == set.find(getNum(m - 1, n - 1))
    }

    private fun getNum(x: Int, y: Int): Int {
        return x * n + y
    }

    private fun handle(x: Int, y: Int) {
        when (grid[x][y]) {
            1 -> {
                detectL(x, y)
                detectR(x, y)
            }
            2 -> {
                detectU(x, y)
                detectD(x, y)
            }
            3 -> {
                detectL(x, y)
                detectD(x, y)
            }
            4 -> {
                detectR(x, y)
                detectD(x, y)
            }
            5 -> {
                detectL(x, y)
                detectU(x, y)
            }
            6 -> {
                detectR(x, y)
                detectU(x, y)
            }
        }
    }

    private fun detectD(x: Int, y: Int) {
        if (x + 1 < m && (grid[x + 1][y] == 2 || grid[x + 1][y] == 5 || grid[x + 1][y] == 6)) {
            set.merge(getNum(x, y), getNum(x + 1, y))
        }
    }

    private fun detectU(x: Int, y: Int) {
        if (x - 1 >= 0 && (grid[x - 1][y] == 2 || grid[x - 1][y] == 3 || grid[x - 1][y] == 4)) {
            set.merge(getNum(x, y), getNum(x - 1, y))
        }
    }

    private fun detectR(x: Int, y: Int) {
        if (y + 1 < n && (grid[x][y + 1] == 1 || grid[x][y + 1] == 5 || grid[x][y + 1] == 3)) {
            set.merge(getNum(x, y), getNum(x, y + 1))
        }
    }

    private fun detectL(x: Int, y: Int) {
        if (y - 1 >= 0 && (grid[x][y - 1] == 1 || grid[x][y - 1] == 4 || grid[x][y - 1] == 6)) {
            set.merge(getNum(x, y), getNum(x, y - 1))
        }
    }
}

class DisjointSet {
    private val f = IntArray(300 * 300) { i -> i }

    fun find(x: Int): Int {
        return if (x == f[x]) x else {
            f[x] = find(f[x]) //找到他的头头
            f[x]
        }
    }

    fun merge(x: Int, y: Int) {
        f[find(f[x])] = find(y)
    }
}

fun main() {
    Solution().hasValidPath(arrayOf(intArrayOf(1,1,1,1,1,1,3)))
}
```
## Conclusion

如果你同我一样热爱数据结构、算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[awesome-kotlin-leetcode][akl]



[title]: https://leetcode-cn.com/problems/check-if-there-is-a-valid-path-in-a-grid/
[akl]: https://github.com/NightXlt/awesome-kotlin-leetcode
