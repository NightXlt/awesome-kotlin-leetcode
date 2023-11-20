package com.blankj.medium._129

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import com.sun.source.tree.Tree

class Solution {
    fun sumNumbers(root: TreeNode?): Int {
        root ?: return 0
        return dfs(root, 0)
    }

    private fun dfs(root: TreeNode?, path: Int): Int {
        root ?: return 0
        val sum = path * 10 + root.`val`
        if (root.left == null && root.right == null) {
            return sum
        }
        return dfs(root.left, sum) + dfs(root.right, sum)
    }
}

fun main() {
    Solution().sumNumbers(
        TreeNode.createTestData("[1,2,3]")
    ).print()
}