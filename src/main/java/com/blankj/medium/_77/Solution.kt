package com.blankj.medium._77

import com.blankj.ext.print


class Solution {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        dfs(n, k, 1, result, mutableListOf())
        return result
    }

    private fun dfs(num: Int, k: Int, start: Int, result: MutableList<List<Int>>, integers: MutableList<Int>) {
        if (integers.size == k) {
            result.add(integers.toList())
            return
        }
        for (i in start..num) {
            integers.add(i)
            dfs(num, k, i + 1, result, integers)
            integers.removeAt(integers.size - 1)
        }
    }
}

fun main() {
    Solution().combine(5, 2).print()
}
