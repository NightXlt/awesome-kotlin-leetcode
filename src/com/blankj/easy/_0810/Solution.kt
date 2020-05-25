package com.blankj.easy._0810

class Solution {
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        if (image[sr][sc] == newColor) return image // sr, sc must be valid
        dfs(image, sr, sc, image[sr][sc], newColor)
        return image
    }

    private fun dfs(image: Array<IntArray>, sr: Int, sc: Int, oldColor: Int, newColor: Int) {
        if (sr !in image.indices || sc !in image[0].indices || image[sr][sc] != oldColor) return
        image[sr][sc] = newColor
        for (dir in dirs) {
            val row = sr + dir[0]
            val col = sc + dir[1]
            if (row in image.indices && col in image[0].indices) dfs(image, row, col, oldColor, newColor)
        }
    }

    companion object {
        @JvmField
        val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, -1))
    }
}