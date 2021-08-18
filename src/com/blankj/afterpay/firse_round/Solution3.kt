package com.blankj.afterpay.firse_round

import com.blankj.coding_interviews._004.print

class Solution3 {

    private var sum = 0

    fun sum(nums: List<List<Pair<Int, Int>>>?): Int {
        nums ?: return 0
        sum = 0
        nums.asSequence()
            .flatten()
            .forEach {
                foldOdd(it.first)
                foldOdd(it.second)
            }
        return sum
    }

    private fun foldOdd(num: Int) {
        if ((num and 0x01) == 1) {
            sum += num
        }
    }
}

fun main() {
    /**
     * test cases
     * 1. null
     * 2. []
     * 3. [[1, 2], [3, 4] ], [[5, 6], [7, 8]]]
     */
    Solution3().sum(null).print()
    Solution3().sum(listOf()).print()
    Solution3().sum(
        listOf(
            listOf(
                Pair(1, 2)
            ), listOf(
                Pair(3, 4)
            ), listOf(
                Pair(5, 6)
            ), listOf(
                Pair(7, 8)
            )
        )
    ).print()
}

