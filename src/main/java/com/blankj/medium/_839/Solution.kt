package com.blankj.medium._839

import com.blankj.ext.print
import com.blankj.structure.MultiDimensionArray

class Solution {
    fun numSimilarGroups(strs: Array<String>): Int {
        if (strs.isEmpty()) return 0
        val find = IntArray(strs.size) { it }
        var count = strs.size
        for (i in strs.indices) {
            for (j in i + 1 until strs.size) {
                if (similar(strs[i], strs[j]) && merge(find, i, j)) {
                    count--
                }
            }
        }
        return count
    }

    private fun similar(s: String, s1: String): Boolean {
        var diffCount = 0
        for (i in s.indices) {
            if (s[i] != s1[i]) diffCount++
        }
        return diffCount <= 2
    }

    private fun merge(find: IntArray, x: Int, y: Int): Boolean {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
            return true
        }
        return false
    }

    // 路径压缩
    private fun find(find: IntArray, x: Int): Int {
        if (x != find[x]) {
            find[x] = find(find, find[x])
        }
        return find[x]
    }
}

fun main() {
    Solution().numSimilarGroups(
        arrayOf("tars","rats","arts","star")
    ).print()
    Solution().numSimilarGroups(
        arrayOf("omv","ovm")
    ).print()
}