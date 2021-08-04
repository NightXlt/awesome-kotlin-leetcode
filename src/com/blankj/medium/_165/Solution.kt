package com.blankj.medium._165

import com.blankj.coding_interviews._004.print


class Solution {

    // Double pointer point two string.
    // Time-Complexity: O(max(m, n)); m,n: two string length
    // Space-Complexity: O(m + n), substring would consume two string size
    private fun getNextChunk(version: String, n: Int, p: Int): Pair<Int, Int> {
        // if pointer is set to the end of string
        // return 0
        if (p > n - 1) return Pair(0, p)
        // find the end of chunk
        val i: Int
        var pEnd = p
        while (pEnd < n && version[pEnd] != '.') {
            ++pEnd
        }
        // retrieve the chunk
        i = if (pEnd != n - 1) {
            version.substring(p, pEnd).toInt()
        } else {
            version.substring(p, n).toInt()
        }
        // find the beginning of next chunk
        return Pair(i, pEnd + 1)
    }

    fun compareVersion(version1: String, version2: String): Int {
        var p1 = 0
        var p2 = 0
        val n1 = version1.length
        val n2 = version2.length

        // compare versions
        var i1: Int
        var i2: Int
        var pair: Pair<Int, Int>
        while (p1 < n1 || p2 < n2) {
            pair = getNextChunk(version1, n1, p1)
            i1 = pair.first
            p1 = pair.second
            pair = getNextChunk(version2, n2, p2)
            i2 = pair.first
            p2 = pair.second
            if (i1 != i2) {
                return if (i1 > i2) 1 else -1
            }
        }
        // the versions are equal
        return 0
    }
}

fun main() {
    Solution().compareVersion("1.0.001", "1.0.1").print()
}