package com.blankj.hard._1044

import com.blankj.ext.print

class Solution {
    fun longestDupSubstring(s: String): String {
        var res = ""
        var left = 0
        var right = s.length
        while (left <= right) {
            val mid = (left + right) / 2
            val x = find(s, mid)
            if (x.isNotEmpty()) {
                res = x
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return res
    }


    private fun find(s: String, len: Int): String {
        var power = 1L
        var hash = 0L
        for (i in 0..<len) {
            hash = hash * PRIME + s[i].code
            power *= PRIME
        }
        val set = mutableSetOf(hash)
        for (i in len..<s.length) {
            hash = hash * PRIME + s[i].code - (power * s[i - len].code)
            if (hash in set) {
                val res = s.substring(i - len + 1, i + 1)
                if (s.indexOf(res) != i) {
                    return res
                }
            }
            set.add(hash)
        }
        return ""
    }

    companion object {
        const val PRIME = 31
    }
}

fun main() {
    Solution().longestDupSubstring("banana").print()
    Solution().longestDupSubstring("abcd").print()
    Solution().longestDupSubstring("dcsopfbhupztcyxctlyxocqwgcgydrxkbbeowdlkcehhslmidwphslbf").print()
}