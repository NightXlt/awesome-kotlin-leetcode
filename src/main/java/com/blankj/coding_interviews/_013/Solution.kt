package com.blankj.coding_interviews._013

import com.blankj.ext.print


class Solution {
    fun movingCount(m: Int, n: Int, k: Int): Int {
        if (k < 0) error("Illegal argument exception")
        val visited = Array(m) { BooleanArray(n) }

        fun dfs(
                i: Int,
                j: Int,
                sum: Int
        ): Int {
            if (i > m - 1 || j > n - 1 || visited[i][j] || sum > k) return 0
            visited[i][j] = true
            return 1 +
                    dfs(i + 1, j, if ((i + 1) % 10 == 0) sum - 8 else sum + 1) +
                    dfs(i, j + 1, if ((j + 1) % 10 == 0) sum - 8 else sum + 1)
        }
        return dfs(0, 0, 0)
    }
}

fun main() {
    Solution().movingCount(2, 3, 1).print()
    Solution().movingCount(2, 3, 2).print()
    Solution().movingCount(3, 1, 0).print()
}
