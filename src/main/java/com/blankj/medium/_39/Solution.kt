package com.blankj.medium._39

import com.blankj.ext.print

class Solution {
    var res = mutableListOf<List<Int>>()

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        if (candidates.isEmpty()) return emptyList()
        candidates.sort()
        res = mutableListOf()
        dfs(candidates, 0, target, 0, mutableListOf())
        return res
    }

    private fun dfs(candidates: IntArray, start: Int, target: Int, sum: Int, path: MutableList<Int>) {
        if (sum == target) {
            res.add(path.toList())
        }
        for (i in start..<candidates.size) {
            if (candidates[i] + sum > target) {
                break
            }
            path.add(candidates[i])
            // 继续深搜以 i 为 start 的集合，题目是无限的吼， 不包含的当溢出后回溯时， 我们可以通过下一个 i 替换掉
            dfs(candidates, i, target, sum + candidates[i], path)
            path.removeLast()
        }
    }
}

fun main() {
    Solution().combinationSum(intArrayOf(2, 3, 6, 7), 7).print()
    Solution().combinationSum(intArrayOf(2, 3, 5), 8).print()
}