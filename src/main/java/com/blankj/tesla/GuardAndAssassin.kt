package com.blankj.tesla

object GuardAndAssassin {
    @JvmStatic
    fun main(args: Array<String>) {
        val case1 = arrayOf(
            "X.....>",
            "..v..X.",
            ".>..X..",
            "A......",
        )
        println("case1=" + solution(case1))
        val case2 = arrayOf(
            "...Xv",
            "AX..^",
            ".XX.."
        )
        println("case2=" + solution(case2))
        val case3 = arrayOf(
            "......>",
            "..v..X.",
            "..v..X.",
            ".>.<...",
            "X......"
        )
        println("case3=" + solution(case3))

        val case4 = arrayOf(
            "...",
            "..A",
        )
        println("case4=" + solution(case4))
        val case5 = arrayOf(
            "A.v",
            "...",
        )
        println("case5=" + solution(case5))

    }

    fun solution(B: Array<String>): Boolean {
        val m = B.size
        val n = B[0].length
        val unreachedPath = Array(m) { BooleanArray(n) } //true meaning unreached coordinate
        val visited = Array(m) { BooleanArray(n) }
        recordUnReachedPath(B, unreachedPath)
        var startRow = -1
        var startCol = -1
        for (i in B.indices) {
            for (j in B.indices) {
                if (B[i][j] == 'A') {
                    startRow = i
                    startCol = j
                }
            }
        }
        return dfs(unreachedPath, visited, startRow, startCol)
    }

    private fun recordUnReachedPath(
        B: Array<String>,
        unreachedPath: Array<BooleanArray>,
    ) {
        val m = B.size
        val n = B[0].length
        for (i in B.indices) {
            for (j in B[i].indices) {
                val c = B[i][j]
                if (isObstacle(c)) {
                    unreachedPath[i][j] = true
                    if (c == '>') {
                        var index = j + 1
                        while (index < n) {
                            if (isObstacle(B[i][index])) break
                            unreachedPath[i][index] = true
                            index++
                        }
                    } else if (c == '<') {
                        var index = j - 1
                        while (index >= 0) {
                            if (isObstacle(B[i][index])) break
                            unreachedPath[i][index] = true
                            index--
                        }
                    } else if (c == '^') {
                        var index = i - 1
                        while (index >= 0) {
                            if (isObstacle(B[index][j])) break
                            unreachedPath[index][j] = true
                            index--
                        }
                    } else if (c == 'v') {
                        var index = i + 1
                        while (index < m) {
                            if (!isObstacle(B[index][j])) break
                            unreachedPath[index][j] = true
                            index++
                        }
                    }
                }
            }
        }
    }

    private fun dfs(unreachedPath: Array<BooleanArray>, visited: Array<BooleanArray>, i: Int, j: Int): Boolean {
        if (i !in unreachedPath.indices || j !in unreachedPath[i].indices) {
            return false
        }
        if (unreachedPath[i][j]) {
            return false
        }
        if (i == unreachedPath.size - 1 && j == unreachedPath[i].size - 1) {
            return true
        }
        if (visited[i][j]) {
            return false
        }
        visited[i][j] = true

        return (dfs(unreachedPath, visited, i + 1, j)
                || dfs(unreachedPath, visited, i - 1, j)
                || dfs(unreachedPath, visited, i, j + 1)
                || dfs(unreachedPath, visited, i, j - 1))
    }

    private fun isObstacle(c: Char): Boolean {
        return c == '>' || c == '<' || c == '^' || c == 'v' || c == 'X'
    }
}