package com.blankj.easy._530

import com.blankj.structure.TreeNode

class Solution {
    private var pre = -1
    private var res = Int.MAX_VALUE
    fun getMinimumDifference(root: TreeNode?): Int {
        pre = -1
        res = Int.MAX_VALUE
        dfs(root)
        return res
    }

    private fun dfs(root: TreeNode?) {
        root ?: return
        dfs(root.left)
        if (pre == -1) {
            pre = root.`val`
        } else {
            res = res.coerceAtMost(root.`val` - pre)
            pre = root.`val`
        }
        dfs(root.right)
    }
}