package com.blankj.medium._77

import com.blankj.ext.print


class Solution {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val lists: MutableList<List<Int>> = mutableListOf()
        val list: List<Int> = listOf()
        dfs(n, k, 1, lists, list)
        return lists
    }

    private fun dfs(num: Int, k: Int, start: Int, lists: MutableList<List<Int>>, list: List<Int>) {
        val integers: MutableList<Int> = list.toMutableList()
        if (integers.size == k) {
            lists.add(integers)
        }
        for (i in start..num) {
            integers.add(i)
            dfs(num, k, i + 1, lists, integers)
            integers.removeAt(integers.size - 1)
        }
    }
}

fun main() {
    Solution().combine(5, 2).print()
}
