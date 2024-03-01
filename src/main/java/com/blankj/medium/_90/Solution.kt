package com.blankj.medium._90

import com.blankj.ext.print


class Solution {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val lists: MutableList<List<Int>> = mutableListOf()
        val list: List<Int> = listOf()
        nums.sort()
        dfs(nums, 0, lists, list)
        return lists
    }

    private fun dfs(nums: IntArray, start: Int, lists: MutableList<List<Int>>, list: List<Int>) {
        val integers: MutableList<Int> = list.toMutableList()
        lists.add(integers)
        for (i in start until nums.size) {
            if (i > start && nums[i] == nums[i - 1]) continue
            integers.add(nums[i])
            dfs(nums, i + 1, lists, integers)
            integers.removeLast()
        }
    }
}

fun main() {
    Solution().subsetsWithDup(intArrayOf(1, 2, 2)).print()
}
