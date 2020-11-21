package com.blankj.coding_interviews._31

import com.blankj.coding_interviews._004.print
import java.util.*


class Solution {

    fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {
        if (pushed.size != popped.size) {
            return false
        }
        val stack = ArrayDeque<Int>()
        var i = 0
        for (num in pushed) {
            stack.push(num)
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop()
                i++
            }
        }
        return stack.isEmpty()
    }
}

fun main() {
    Solution().validateStackSequences(intArrayOf(1, 2, 3, 4, 5), intArrayOf(4, 5, 3, 2, 1)).print()
    Solution().validateStackSequences(intArrayOf(1, 2, 3, 4, 5), intArrayOf(4, 5, 3, 2)).print()
    Solution().validateStackSequences(intArrayOf(1, 2, 3, 4, 5), intArrayOf(4, 5, 3, 1, 2)).print()
}
