package com.blankj.hard._140

class Solution {
    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        if (s.isEmpty()) return emptyList()
        return dfs(s, wordDict.toSet(), 0)
    }

    val map = mutableMapOf<Int, List<String>>()

    fun dfs(s: String, wordDict: Set<String>, start: Int): List<String> {
        if (start == s.length) return emptyList()
        if (start in map) return map.getValue(start)
        val res = mutableListOf<String>()
        for (i in start..<s.length) {
            val subString = s.substring(start, i + 1)
            if (subString !in wordDict) continue
            if (i + 1 == s.length) {
                res.add(subString) // 找到最后一段时直接跳出循环
                continue
            }
            val list = dfs(s, wordDict, i + 1)
            res.addAll(list.map { "$subString $it" })
        }
        map[start] = res
        return res
    }
}