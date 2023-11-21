import com.blankj.ext.print

fun canAssassinReachDestination(B: Array<String>): Boolean {
    val n = B.size
    val m = B[0].length
    val visited = Array(n) { BooleanArray(m) }
    val directions = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
    var assassinRow = -1
    var assassinCol = -1

    // 寻找刺客的起始位置
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (B[i][j] == 'A') {
                assassinRow = i
                assassinCol = j
                break
            }
        }
    }

    // 检查当前位置是否安全，即不是障碍物也不在守卫的视线中
    fun isSafe(row: Int, col: Int, B: Array<String>): Boolean {
        if (row !in B.indices || col !in B[0].indices) return false
        val n = B.size
        val m = B[0].length

        // 定义守卫视线方向
        val directions = mapOf('<' to (0 to -1), '>' to (0 to 1), '^' to (-1 to 0), 'v' to (1 to 0))

        // 检查当前位置是否为障碍物或在守卫的视线中
        if (B[row][col] == 'X') return false

        // 检查每个守卫的视线方向
        for ((guard, dir) in directions) {
            val (dr, dc) = dir
            var r = row + dr
            var c = col + dc

            while (r in 0 until n && c in 0 until m) {
                when (B[r][c]) {
                    'X' -> break // 遇到障碍物，守卫视线被挡住
                    guard -> return false // 当前方向上有守卫，位置不安全
                    in directions.keys -> break // 遇到另一个守卫，视线被挡住
                }
                r += dr
                c += dc
            }
        }
        return true // 当前位置安全
    }

    // 使用 DFS 进行搜索
    fun dfs(row: Int, col: Int): Boolean {
        if (row == n - 1 && col == m - 1) return true // 到达目标
        visited[row][col] = true
        for (dir in directions) {
            val newRow = row + dir[0]
            val newCol = col + dir[1]
            if (isSafe(newRow, newCol, B) && !visited[newRow][newCol]) {
                if (dfs(newRow, newCol)) return true
            }
        }
        return false
    }

    return if (isSafe(assassinRow, assassinCol, B)) dfs(assassinRow, assassinCol) else false
}
fun main() {
    canAssassinReachDestination(
        arrayOf(
            "...Xv",
            "AX..^",
            ".XX..",
        )
    ).print()
}