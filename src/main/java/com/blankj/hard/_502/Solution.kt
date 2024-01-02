package com.blankj.hard._502

import com.blankj.ext.print
import java.util.*

class Solution {
    fun findMaximizedCapital(
        k: Int,
        w: Int,
        profits: IntArray,
        capital: IntArray
    ): Int {
        val arr = Array(profits.size) { IntArray(2) }
        for (i in profits.indices) {
            arr[i][0] = capital[i]
            arr[i][1] = profits[i]
        }
        arr.sortBy { it[0] }
        val queue = PriorityQueue<Int> { o1, o2 -> o2 - o1 }
        var cur = 0
        var res = w
        for (i in 1..k) {
            while (cur < profits.size && arr[cur][0] <= res) {
                queue.add(arr[cur][1])
                cur++
            }
            if (queue.isNotEmpty()) {
                res += queue.poll()
            } else {
                break
            }
        }
        return res
    }
}

fun main() {
    Solution().findMaximizedCapital(
        2, 0,
        intArrayOf(1, 2, 3),
        intArrayOf(0, 1, 1),
    ).print()
}