package com.blankj.easy._202

import com.blankj.ext.print

class Solution {
    fun isHappy(n: Int): Boolean {
        var cur = n
        val set = mutableSetOf(cur)
        while (cur != 1) {
            cur = getNext(cur)
            if (cur in set) return false
            set.add(cur)
        }
        return true
    }

    private fun getNext(n: Int): Int {
        var res = 0
        var cur = n
        while (cur != 0) {
            val digit = cur % 10
            res += digit * digit
            cur /= 10
        }
        return res
    }
}

fun main() {
    Solution().isHappy(19).print()
    Solution().isHappy(2).print()
}