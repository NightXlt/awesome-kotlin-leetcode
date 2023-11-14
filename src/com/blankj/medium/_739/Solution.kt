package com.blankj.medium._739

import com.blankj.ext.print


class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        val answers = IntArray(temperatures.size)
        for (i in temperatures.indices) {
            while (stack.isNotEmpty() && temperatures[i] > temperatures[stack.last()]) {
                val prev = stack.removeLast()
                answers[prev] = i - prev
            }
            stack.add(i)
        }
        return answers
    }
}

fun main() {
    Solution().dailyTemperatures(intArrayOf(73,74,75,71,69,72,76,73)).print()
    Solution().dailyTemperatures(intArrayOf(30,40,50,60)).print()
    Solution().dailyTemperatures(intArrayOf(30,60,90)).print()
    Solution().dailyTemperatures(intArrayOf(89,62,70,58,47,47,46,76,100,70)).print()
}