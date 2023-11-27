package com.blankj.medium._40

import com.blankj.ext.print

class Solution {
    var res = mutableListOf<List<Int>>()

    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
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
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue
            }
            path.add(candidates[i])
            dfs(candidates, i + 1, target, sum + candidates[i], path)
            path.removeLast()
        }
    }
}

fun main() {
    Solution().combinationSum2(intArrayOf(2, 5, 2, 1, 2), 5).print()
    Solution().combinationSum2(intArrayOf(10, 1, 2, 7, 6, 1, 5), 8).print()
}