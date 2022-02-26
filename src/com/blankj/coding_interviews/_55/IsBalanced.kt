package com.blankj.coding_interviews._55

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import kotlin.math.abs
import kotlin.math.max

// https://leetcode-cn.com/problems/balanced-binary-tree/submissions/
class IsBalanced {
    fun isBalanced(root: TreeNode?): Boolean = if (root == null) {
        true
    } else {
        maxChildDep(root) != -1
    }

    /**
     * dfs: from bottom to up
     */
    private fun maxChildDep(root: TreeNode?): Int {
        root ?: return 0
        val leftDepth = maxChildDep(root.left)
        if (leftDepth == -1) return -1
        val rightDepth = maxChildDep(root.right)
        if (rightDepth == -1) return -1
        val diff = abs(leftDepth - rightDepth)
        return if (diff <= 1) max(leftDepth, rightDepth) + 1 else -1
    }
}

fun main() {
    IsBalanced().isBalanced(TreeNode.createTestData("[1,2,2,3,3,null,null,4,4]")).print()
}