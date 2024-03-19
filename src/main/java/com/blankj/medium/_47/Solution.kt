package com.blankj.medium._47

import com.blankj.ext.print

class Solution {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val lists: MutableList<List<Int>> = mutableListOf()
        dfs(nums, 0, lists)
        return lists
    }

    private fun swap(nums: IntArray, m: Int, n: Int) {
        if (m == n) return
        nums[m] = nums[n].also {
            nums[n] = nums[m]
        }
    }

    private fun dfs(nums: IntArray, start: Int, lists: MutableList<List<Int>>) {
        if (start == nums.size) {
            lists.add(nums.toList())
            return
        }
        for (i in start until nums.size) {
            if (!isSwapped(nums, start, i)) {
                swap(nums, i, start)
                dfs(nums, start + 1, lists)
                swap(nums, i, start)
            }
        }
    }

    private fun isSwapped(nums: IntArray, start: Int, end: Int): Boolean {
        return (start..<end).any { nums[it] == nums[end] }
    }
}

fun main() {
    Solution().permuteUnique(intArrayOf(1,1,2)).print()
}
