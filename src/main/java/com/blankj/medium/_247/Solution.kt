package com.blankj.medium._247

import com.blankj.ext.print

class Solution {
    fun findStrobogrammatic(n: Int): List<String> {
        if (n < 1) return emptyList()
        val res = findStrobogrammaticWithPrefixZero(n)
        return res.filterNot {
            it.length > 1 && it.first() == '0'
        }
    }

    private fun findStrobogrammaticWithPrefixZero(n: Int): MutableList<String> {
        val queue = ArrayDeque<String>()
        val start = n % 2
        if (start == 0) {
            queue.add("")
        } else {
            queue.addAll(listOf("0", "1", "8"))
        }
        for (i in start..<n step 2) {
            val size = queue.size
            repeat(size) {
                val prevStrobogrammatic = queue.removeFirst()
                queue.add("0" + prevStrobogrammatic + "0")
                queue.add("1" + prevStrobogrammatic + "1")
                queue.add("6" + prevStrobogrammatic + "9")
                queue.add("8" + prevStrobogrammatic + "8")
                queue.add("9" + prevStrobogrammatic + "6")
            }
        }
        return queue.toMutableList()
    }
}

fun main() {
    Solution().findStrobogrammatic(2).print()
    Solution().findStrobogrammatic(1).print()
}