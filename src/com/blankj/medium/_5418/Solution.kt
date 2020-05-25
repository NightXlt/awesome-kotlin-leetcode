package com.blankj.medium._5418

import com.blankj.structure.TreeNode

class Solution {
    var res = 0

    fun pseudoPalindromicPaths(root: TreeNode?): Int {
        root ?: return 0
        dfs(root, 0)
        return res
    }

    private fun dfs(root: TreeNode, cur: Int) {
        val cur = cur xor (1 shl root.`val`)
        if (root.left == null && root.right == null) {
            if (cur == 0 || cur and (cur - 1) == 0) res++
            return
        }
        root.left?.run { dfs(this, cur) }
        root.right?.run { dfs(this, cur) }
    }
}