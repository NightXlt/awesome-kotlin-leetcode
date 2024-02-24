package com.blankj.hard._149

import kotlin.math.abs
import kotlin.math.max

class Solution {
    fun maxPoints(points: Array<IntArray>): Int {
        val n = points.size
        if (n <= 2) return n
        var res = 0
        for (i in 0..<n) {
            if (res >= n - i || res > n / 2) {
                break
            }
            val map = mutableMapOf<Int, Int>()
            var maxN = 0
            for (j in i + 1..<n) {
                var dx = points[j][0] - points[i][0]
                var dy = points[j][1] - points[i][1]
                when {
                    dx == 0 -> dy = 1
                    dy == 0 -> dx = 1
                    else -> {
                        if (dy < 0) {
                            dx = -dx
                            dy = -dy
                        }
                        val gcdXY = gcd(abs(dx), abs(dy))
                        dx /= gcdXY
                        dy /= gcdXY
                    }
                }
                val key = dy + dx * 1001
                map.merge(key, 1, Integer::sum)
            }
            maxN = (map.values.maxOrNull() ?: 0) + 1
            res = max(maxN, res)
        }
        return res
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b != 0) gcd(b, a % b) else a
    }
}