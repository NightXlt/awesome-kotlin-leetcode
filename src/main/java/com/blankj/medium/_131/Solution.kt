package com.blankj.medium._131

import com.blankj.ext.print

import java.util.ArrayList
class Solution {
    fun partition(s: String): List<List<String>> {
        if (s.isEmpty()) return emptyList()
        val dp = Array(s.length) { BooleanArray(s.length) }
        buildLookupTable(dp, s)
        val stack = mutableListOf<String>()
        val res = mutableListOf<List<String>>()
        fun dfs(start: Int) {
            if (start == s.length) {
                res.add(stack.toList())
                return
            }
            for (i in start..<s.length) {
                if (!dp[start][i]) continue
                stack.add(s.substring(start, i + 1))
                dfs(i + 1)
                stack.removeLast()
            }
        }
        dfs(0)
        return res
    }

    private fun buildLookupTable(dp: Array<BooleanArray>, s: String) {
        for (right in s.indices) {
            for (left in 0..right) {
                dp[left][right] = s[left] == s[right] && (right - left <= 2 || dp[left + 1][right - 1])
            }
        }
    }
}

fun main() {
    val list = ArrayList<Int>().apply {
        add(1)
        add(2)

    }
    println(list.removeLast())
    Solution().partition("aab").print()
}