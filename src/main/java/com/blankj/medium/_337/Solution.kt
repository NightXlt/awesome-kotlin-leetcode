package com.blankj.medium._337

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import kotlin.math.max


class Solution {
    private val f = mutableMapOf<TreeNode?, Int>().withDefault { 0 }
    private val g = mutableMapOf<TreeNode?, Int>().withDefault { 0 }
    fun rob(root: TreeNode?): Int {
        root ?: return 0
        dfs(root)
        return max(f.getValue(root), g.getValue(root))
    }

    private fun dfs(root: TreeNode?) {
        if (root == null) {
            return
        }
        dfs(root.left)
        dfs(root.right)
        f[root] = g.getValue(root.left) + g.getValue(root.right) + root.`val`
        g[root] = f.getValue(root.left).coerceAtLeast(g.getValue(root.left)) + f.getValue(root.right).coerceAtLeast(g.getValue(root.right))
    }
}

fun main() {
    Solution().rob(TreeNode.createTestData("[3,2,3,null,3,null,1]")).print()
    Solution().rob(TreeNode.createTestData("[3,4,5,1,3,null,1]")).print()
}