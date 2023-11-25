package com.blankj.medium._875

import com.blankj.ext.print

class Solution {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        val max = piles.maxOrNull() ?: return -1
        var left = 1
        var right = max
        while (left <= right) {
            val mid = (left + right) shr 1
            val hours: Int = getHours(piles, mid)
            if (hours <= h) {
                if (mid == 1 || getHours(piles, mid - 1) > h) {
                    return mid
                }
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return -1
    }

    private fun getHours(piles: IntArray, speed: Int): Int {
        return piles.fold(0) { acc, i ->
            acc + (i + speed - 1) / speed
        }
    }
}

fun main() {
    Solution().minEatingSpeed(intArrayOf(3, 6, 7, 11), 8).print()
    Solution().minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 5).print()
    Solution().minEatingSpeed(intArrayOf(312884470), 968709470).print()
}