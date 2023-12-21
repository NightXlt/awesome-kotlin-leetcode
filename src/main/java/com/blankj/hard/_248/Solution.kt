package com.blankj.hard._248

import com.blankj.ext.print

class Solution {
    fun strobogrammaticInRange(low: String, high: String): Int {
        val res = findStrobogrammaticWithPrefixZero(high.length, low.toLong(), high.toLong())
        return res.filterNot {
            it.length > 1 && it.first() == '0'
        }.size
    }

    private fun findStrobogrammaticWithPrefixZero(n: Int, low: Long, high: Long) : List<String>{
        val queue = ArrayDeque<String>()
        val start = n % 2
        val res = mutableListOf<String>()
        queue.addAll(listOf("", "0", "1", "8"))
        for (i in start..<n step 2) {
            val size = queue.size
            repeat(size) {
                val str = queue.removeFirst()
                var prevStrobogrammatic = str.toLongOrNull()
                if (prevStrobogrammatic in low..high) {
                    res.add(str)
                }
                if ((prevStrobogrammatic ?: 0) > high) {
                    return res
                }
                queue.add("0" + str + "0")
                queue.add("1" + str + "1")
                queue.add("6" + str + "9")
                queue.add("8" + str + "8")
                queue.add("9" + str + "6")
            }
        }
        res.addAll(queue.filter { it.toLongOrNull() in low..high })
        return res
    }

}

fun main() {
    Solution().strobogrammaticInRange("50", "100").print()
    Solution().strobogrammaticInRange("0", "0").print()
    Solution().strobogrammaticInRange("0", "1680").print()
    Solution().strobogrammaticInRange("0", "100000000000000").print()
}