package com.blankj.medium._46

import com.blankj.coding_interviews._004.print

internal class Solution {
    fun permute(nums: IntArray): List<List<Int>> {
        val lists: MutableList<List<Int>> = mutableListOf()
        dfs(nums, 0, lists)
        return lists
    }

    private fun swap(nums: IntArray, m: Int, n: Int) {
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
            swap(nums, i, start)
            dfs(nums, start + 1, lists)
            swap(nums, i, start)
        }
    }
}

fun main() {
    Solution().permute(intArrayOf(1, 2, 3, 4, 5)).print()
}
