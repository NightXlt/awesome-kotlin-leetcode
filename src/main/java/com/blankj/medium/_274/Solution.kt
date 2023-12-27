package com.blankj.medium._274

import com.blankj.ext.print

class Solution {
    fun hIndex(citations: IntArray): Int {
        val count = IntArray(citations.size + 1)
        for (citation in citations) {
            if (citation > citations.size) {
                count[count.lastIndex]++
            } else {
                count[citation]++
            }
        }
        var total = 0
        for (h in count.indices.reversed()) {
            total += count[h]
            if (total >= h) {
                return h
            }
        }
        return 0
    }
}

fun main() {
    Solution().hIndex(intArrayOf(3, 0, 6, 1, 5)).print()
    Solution().hIndex(intArrayOf(1, 3, 1)).print()
}