package com.blankj.medium._218

import com.blankj.coding_interviews._004.print
import kotlin.math.max
// https://leetcode-cn.com/problems/the-skyline-problem/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--45/
class Solution {
    fun getSkyline(buildings: Array<IntArray>): List<List<Int>> {
        if (buildings.isEmpty()) return emptyList()
        return divide(buildings, 0, buildings.lastIndex)
    }

    private fun divide(
            buildings: Array<IntArray>,
            start: Int,
            end: Int
    ): List<List<Int>> {
        val res: MutableList<List<Int>> = mutableListOf()
        if (start == end) {
            res.add(
                    listOf(
                            buildings[start][0],
                            buildings[start][2]
                    )
            )
            res.add(
                    listOf(
                            buildings[start][1],
                            0
                    )
            )
            return res
        }
        val mid = (start + end) / 2
        val skyLineLeftPart = divide(buildings, start, mid)
        val skyLineRightPart = divide(buildings, mid + 1, end)
        merge(skyLineLeftPart, skyLineRightPart, res)
        return res
    }

    private fun merge(
            skyLineLeftPart: List<List<Int>>,
            skyLineRightPart: List<List<Int>>,
            res: MutableList<List<Int>>
    ) {
        var i = 0
        var j = 0

        var h1 = 0
        var h2 = 0
        while (i < skyLineLeftPart.size || j < skyLineRightPart.size) {
            val x1 = skyLineLeftPart.getOrNull(i)?.get(0)?.toLong() ?: Long.MAX_VALUE
            val x2 = skyLineRightPart.getOrNull(j)?.get(0)?.toLong() ?: Long.MAX_VALUE
            var x: Long
            when {
                x1 < x2 -> {
                    h1 = skyLineLeftPart[i][1]
                    x = x1
                    i++
                }
                x2 < x1 -> {
                    h2 = skyLineRightPart[j][1]
                    x = x2
                    j++
                }
                else -> {
                    h1 = skyLineLeftPart[i][1]
                    h2 = skyLineRightPart[j][1]
                    x = x1
                    i++
                    j++
                }
            }
            val height = max(h1, h2)
            if (res.isEmpty() || height != res.last()[1]) {
                res.add(
                        listOf(
                                x.toInt(),
                                height
                        )
                )
            }
        }
    }
}

fun main() {
    Solution().getSkyline(arrayOf(
            intArrayOf(2, 9, 10),
            intArrayOf(3, 7, 15),
            intArrayOf(5, 12, 12),
            intArrayOf(15, 20, 10),
            intArrayOf(19, 24, 8)
    )).print()
}
