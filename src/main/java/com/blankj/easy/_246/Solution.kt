package com.blankj.easy._246

import com.blankj.ext.print

class Solution {

    private val mapping = mapOf(
        '1' to '1',
        '6' to '9',
        '9' to '6',
        '8' to '8',
        '0' to '0',
    )

    fun isStrobogrammatic(num: String): Boolean {
        if (num.isEmpty()) return true
        for (i in num.indices) {
            if (num[i] !in mapping) return false
            if (mapping.getValue(num[i]) != num[num.lastIndex - i]) return false
        }
        return true
    }
}

fun main() {
    Solution().isStrobogrammatic("69").print()
    Solution().isStrobogrammatic("88").print()
    Solution().isStrobogrammatic("962").print()
    Solution().isStrobogrammatic("11").print()
}