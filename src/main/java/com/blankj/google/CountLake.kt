package com.blankj.google

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class CountLake {
    private var dirLand: Array<IntArray> = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(-1, 0),
        intArrayOf(1, 0),
        intArrayOf(-1, 1),
        intArrayOf(1, 1),
        intArrayOf(-1, -1),
        intArrayOf(1, -1)
    )
    private var dirWater: Array<IntArray> = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
    private var xmin: Int = 0
    private var ymin: Int = 0
    private var ymax: Int = 0
    private var xmax: Int = 0
    private var count: Int = 0
    private var set: HashSet<ArrayList<*>> = HashSet()

    private fun knowTheBounds(image: Array<CharArray>, x: Int, y: Int) {
        for (step in dirLand) {
            val xnew = x + step[0]
            val ynew = y + step[1]

            val temp = ArrayList<Int>()
            temp.add(xnew)
            temp.add(ynew)

            if (isValid(image, xnew, ynew) && image[xnew][ynew] == 'X' && !set.contains(temp)) {
                xmin = min(xnew.toDouble(), xmin.toDouble()).toInt()
                xmax = max(xmax.toDouble(), xnew.toDouble()).toInt()
                ymin = min(ymin.toDouble(), ynew.toDouble()).toInt()
                ymax = max(ymax.toDouble(), ynew.toDouble()).toInt()

                set.add(temp)
                knowTheBounds(image, xnew, ynew)
            }
        }
    }

    private fun isValid(image: Array<CharArray>, x: Int, y: Int): Boolean {
        if (x < 0 || y < 0 || x >= image.size || y >= image[0].size) {
            return false
        }
        return true
    }

    private fun isInBounds(x: Int, y: Int): Boolean {
        if (x < xmin || y < ymin || x >= xmax || y >= ymax) {
            return false
        }
        return true
    }

    private fun paintTheBounds(image: Array<CharArray>) {
        for (i in xmin..xmax) {
            checkAndUpdateBounds(image, i, ymin)
            checkAndUpdateBounds(image, i, ymax)
        }

        for (i in ymin..ymax) {
            checkAndUpdateBounds(image, xmin, i)
            checkAndUpdateBounds(image, xmax, i)
        }
    }

    private fun checkAndUpdateBounds(image: Array<CharArray>, x: Int, y: Int) {
        if (image[x][y] != ',') return
        image[x][y] = 'O'
        for (step in dirWater) {
            val x1 = x + step[0]
            val y1 = y + step[1]

            if (isInBounds(x1, y1) && image[x1][y1] == ',') {
                image[x1][y1] = 'O'
                paintTheBounds(image, x1, y1)
            }
        }
    }

    private fun paintTheBounds(image: Array<CharArray>, x: Int, y: Int) {
        for (step in dirWater) {
            val x1 = x + step[0]
            val y1 = y + step[1]

            if (isInBounds(x1, y1) && image[x1][y1] == ',') {
                image[x1][y1] = 'O'
                paintTheBounds(image, x1, y1)
            }
        }
    }

    fun countLakes(image: Array<CharArray>): Int {
        xmin = image.size
        ymin = image[0].size
        knowTheBounds(image, 1, 1)

        paintTheBounds(image)
        for (i in xmin..xmax) {
            for (j in ymin..ymax) {
                if (image[i][j] == ',') {
                    count++
                    image[i][j] = count.toChar()
                    searchForAdjacent(image, i, j)
                }
            }
        }
        return count
    }

    private fun searchForAdjacent(image: Array<CharArray>, i: Int, j: Int) {
        for (step in dirWater) {
            val xpos = i + step[0]
            val ypos = j + step[1]

            if (image[xpos][ypos] == ',') {
                image[xpos][ypos] = count.toChar()
                searchForAdjacent(image, xpos, ypos)
            }
        }
    }
}

fun main() {
    val image = arrayOf(
        charArrayOf(',', ',', ',', ',', ',', ',', ','),
        charArrayOf(',', 'X', 'X', 'X', 'X', 'X', 'X'),
        charArrayOf(',', 'X', ',', ',', 'X', ',', 'X'),
        charArrayOf(',', 'X', 'X', 'X', 'X', 'X', ','),
        charArrayOf(',', ',', ',', ',', 'X', ',', ','),
        charArrayOf('X', 'X', ',', ',', 'X', ',', ',')
    )
    CountLake().countLakes(image).print()
}