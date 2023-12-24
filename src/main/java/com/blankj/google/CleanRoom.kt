package com.blankj.google

class Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    fun move(): Boolean {
        TODO()
    }

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    fun turnLeft() {}
    fun turnRight() {}

    // Clean the current cell.
    fun clean() {}
}

class CleanRoom {

    val dirs = arrayOf(
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1),
    )
    val visited = mutableSetOf<Pair<Int, Int>>()

    private var robot: Robot? = null

    private fun goBack() {
        robot!!.turnRight()
        robot!!.turnRight()
        robot!!.move()
        robot!!.turnRight()
        robot!!.turnRight()
    }

    private fun backtrack(row: Int, col: Int, dir: Int) {
        visited.add(Pair(row, col))
        robot!!.clean()
        for (i in dirs.indices) {
            val nextDir = (dir + i) % 4
            val (rowDiff, colDiff) = dirs[nextDir]
            val nextRow = row + rowDiff
            val nextCol = col + colDiff
            if (Pair(nextRow, nextCol) !in visited && robot!!.move()) {
                backtrack(nextRow, nextCol, nextDir)
                goBack()
            }
            // 把机器人的脸转过来
            robot!!.turnRight()
        }
    }



    fun cleanRoom(robot: Robot) {
        this.robot = robot
        backtrack(0, 0, 0)
    }
}