package com.blankj.hard._124

import com.blankj.structure.TreeNode

class Solution {
    var res = Int.MIN_VALUE
    fun maxPathSum(root: TreeNode?): Int {
        root ?: return 0
        res = Int.MIN_VALUE
        dfs(root)
        return res
    }

    private fun dfs(root: TreeNode?): Int {
        root ?: return 0
        val leftMaxSum = dfs(root.left)
        val rightMaxSum = dfs(root.right)
        res = maxOf(res, leftMaxSum.coerceAtLeast(0) + rightMaxSum.coerceAtLeast(0) + root.`val`)
        return maxOf(0, leftMaxSum, rightMaxSum) + root.`val`
    }
}