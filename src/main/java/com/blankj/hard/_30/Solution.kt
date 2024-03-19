package com.blankj.hard._30

import com.blankj.ext.print

class Solution {
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        if (words.isEmpty() || s.isEmpty()) return emptyList()
        val m = words.size
        val w = words.first().length
        val n = s.length
        val count = mutableMapOf<String, Int>()
        words.forEach { count.merge(it, 1, Integer::sum) }
        val res = mutableListOf<Int>()
        for (i in 0..<w) {
            val curMap = mutableMapOf<String, Int>()
            for (j in i..n - w step w) {
                val cur = s.substring(j, j + w)
                if (j >= i + (m * w)) {
                    val index = j - (m * w)
                    val prev = s.substring(index, index + w)
                    if (curMap[prev] == 1) {
                        curMap.remove(prev)
                    } else {
                        curMap[prev] = curMap.getValue(prev) - 1
                    }
                }
                curMap.merge(cur, 1, Integer::sum)
                if (cur in count && curMap[cur] == count[cur] && curMap == count) {
                    res.add(j - (m - 1) * w)
                }
            }
        }
        return res
    }
}

fun main() {
    Solution().findSubstring(
        "aaa",
        arrayOf("a", "a")
    ).print()
}