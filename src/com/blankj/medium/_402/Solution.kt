package com.blankj.medium._402

import com.blankj.coding_interviews._004.print
import java.util.*

class Solution {
    fun removeKdigits(num: String, k: Int): String {
        if (num.isBlank() || num.length <= k) {
            return "0"
        }
        val deque: Deque<Char> = LinkedList<Char>()
        var k = k
        for (c in num) {
            while (deque.isNotEmpty() && k > 0 && deque.peekLast() > c) {
                deque.pollLast()
                k--
            }
            deque.addLast(c)
        }
        repeat(k) {
            if (deque.isNotEmpty()) {
                deque.pollLast()
            }
        }
        val res = StringBuilder()
        var leadingZero = true
        while (deque.isNotEmpty()) {
            val digit = deque.pollFirst()
            if (leadingZero && digit == '0') continue
            leadingZero = false
            res.append(digit)
        }
        return if (res.isEmpty()) "0" else res.toString()
    }
}

fun main() {
    Solution().removeKdigits("1432219", 3).print()
    Solution().removeKdigits("10200", 1).print()
    Solution().removeKdigits("10", 2).print()
}