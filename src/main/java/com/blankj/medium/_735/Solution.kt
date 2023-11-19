package com.blankj.medium._735

import com.blankj.ext.print

class Solution {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        for (asteroid in asteroids) {
            while (stack.isNotEmpty() && stack.last() > 0 && stack.last() < -asteroid) {
                stack.removeLast()
            }
            // equals case
            if (stack.isNotEmpty() && asteroid < 0 && stack.last() == -asteroid) {
                stack.removeLast()
            } else if (stack.isEmpty() || asteroid > 0 || stack.last() < 0) {
                stack.add(asteroid)
            }
        }
        return stack.toIntArray()
    }
}

fun main() {
    Solution().asteroidCollision(
        intArrayOf(5, 10, -5)
    ).print()
    Solution().asteroidCollision(
        intArrayOf(8, -8)
    ).print()
    Solution().asteroidCollision(
        intArrayOf(10, 2, -5)
    ).print()
}