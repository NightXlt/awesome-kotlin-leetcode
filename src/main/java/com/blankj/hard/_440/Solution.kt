package com.blankj.hard._440

import com.blankj.ext.print
import kotlin.math.min

class Solution {
    fun findKthNumber(n: Int, k: Int): Int {
        var cur = 1
        var k = k - 1
        while (k >= 0) {
            val count = getNodes(n, cur)
            if (k >= count) { // go right
                k -= count
                cur += 1
            } else { // go down
                k -= 1
                cur *= 10
            }
        }
        return cur
    }

    private fun getNodes(n: Int, cur: Int): Int {
        var next: Long = cur + 1L
        var totalNodes = 0L
        var cur = cur.toLong()
        while (cur <= n) {
            totalNodes += min(n - cur + 1, next - cur)
            next *= 10
            cur *= 10
        }
        return totalNodes.toInt()
    }
}

fun main() {
    Solution().findKthNumber(13, 2).print()
}