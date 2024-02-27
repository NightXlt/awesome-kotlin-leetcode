package com.blankj.hard._124

import com.blankj.structure.TreeNode
import kotlin.math.max

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
        val leftMaxSum = max(dfs(root.left), 0)
        val rightMaxSum = max(dfs(root.right), 0)
        res = max(res, leftMaxSum + rightMaxSum + root.`val`)
        return max(leftMaxSum, rightMaxSum) + root.`val`
    }
}