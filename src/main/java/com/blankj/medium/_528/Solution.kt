package com.blankj.medium._528

import com.blankj.ext.print
import kotlin.random.Random

class Solution(w: IntArray) {
    private val sums = IntArray(w.size)
    private var total = w.foldIndexed(0) { index, acc, value ->
        sums[index] = value + acc
        sums[index]
    }


    fun pickIndex(): Int {
        val target = Random.nextInt(total)
        var left = 0
        var right = sums.lastIndex
        while (left <= right) {
            val mid = (left + right) shr 1
            if (sums[mid] <= target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

}

fun main(args: Array<String>) {
    val solution = Solution(intArrayOf(1, 3))
    solution.pickIndex().print()
    solution.pickIndex().print()
    solution.pickIndex().print()
    solution.pickIndex().print()
}