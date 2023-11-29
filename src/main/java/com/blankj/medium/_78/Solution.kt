package com.blankj.medium._78

import com.blankj.ext.print


class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val lists: MutableList<List<Int>> = mutableListOf()
        val list: List<Int> = listOf()
        dfs(nums, 0, lists, list)
        return lists
    }

    private fun dfs(nums: IntArray, start: Int, lists: MutableList<List<Int>>, list: List<Int>) {
        val integers: MutableList<Int> = list.toMutableList()
        lists.add(integers)
        for (i in start until nums.size) {
            integers.add(nums[i])
            dfs(nums, i + 1, lists, integers)
            integers.removeAt(integers.size - 1)
        }
    }
}

fun main() {
    Solution().subsets(intArrayOf(1, 2, 3)).print()
    Solution().subsets(intArrayOf(1, 2)).print()
}