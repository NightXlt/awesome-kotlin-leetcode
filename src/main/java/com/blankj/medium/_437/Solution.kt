package com.blankj.medium._437

import com.blankj.structure.TreeNode

class Solution {
    // map stored root-node sum to sum's count
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        root ?: return 0
        val map = mutableMapOf(0L to 1)
        return dfs(root, targetSum, map, 0)
    }

    private fun dfs(
        root: TreeNode?,
        targetSum: Int,
        map: MutableMap<Long, Int>,
        path: Long
    ): Int {
        root ?: return 0
        val curSum = path + root.`val`
        var count = map.getOrDefault(curSum - targetSum, 0)
        map[curSum] = map.getOrDefault(curSum, 0) + 1
        count += dfs(root.left, targetSum, map, curSum)
        count += dfs(root.right, targetSum, map, curSum)
        map[curSum] = map.getValue(curSum) - 1
        return count
    }
}