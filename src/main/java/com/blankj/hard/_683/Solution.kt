package com.blankj.hard._683

import com.blankj.ext.print
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class Solution {
    fun kEmptySlots(bulbs: IntArray, k: Int): Int {
        val mins = ArrayDeque<Int>()
        val days = IntArray(bulbs.size)
        for ((i, bulb) in bulbs.withIndex()) {
            days[bulb - 1] = i + 1
        }
        repeat(min(k + 1, days.size)) { i ->
            while (mins.size != 0 && days[mins.last()] > days[i]) {
                mins.removeLast()
            }
            mins.addLast(i)
        }
        var ans = -1
        for (i in k + 1 until days.size) {
            val prev = mins.first()
            if (prev == i - k - 1) mins.removeFirst()
            while (mins.isNotEmpty() && days[mins.last()] > days[i]) {
                mins.removeLast()
            }
            if (mins.isEmpty() && prev == i - k - 1) {
                val possibleAns = max(days[prev], days[i])
                if (ans == -1 || possibleAns < ans) {
                    ans = possibleAns
                }
            }
            mins.addLast(i)
        }
        return ans
    }

    fun kEmptySlotsWithMuchTime(bulbs: IntArray, k: Int): Int {
        val set = TreeSet<Int>()
        for ((index, bulb) in bulbs.withIndex()) {
            val prev = set.floor(bulb)
            if (prev != null && abs(prev - bulb) - 1 == k) {
                return index + 1
            }
            val next = set.ceiling(bulb)
            if (next != null && abs(next - bulb) - 1 == k) {
                return index + 1
            }
            set.add(bulb)
        }
        return -1
    }
}

fun main() {
    Solution().kEmptySlots(intArrayOf(1, 3, 2), 1).print()
    Solution().kEmptySlots(intArrayOf(1, 3, 4, 2), 1).print()
}