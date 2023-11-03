package com.blankj.medium._222

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import com.sun.source.tree.Tree

class Solution {

    fun countNodes(root: TreeNode?): Int {
        root ?: return 0
        val leftHeight = treeHeight(root.left)
        val rightHeight = treeHeight(root.right)
        if (leftHeight == rightHeight) {
            return (1 shl (leftHeight)) + countNodes(root.right)
        } else {
            return (1 shl rightHeight) + countNodes(root.left)
        }

    }

    private fun treeHeight(root: TreeNode?): Int {
        var level = 0
        var node = root
        while (node != null) {
            node = node.left
            level++
        }
        return level
    }
}

fun main() {
    Solution().countNodes(TreeNode.createTestData("[1,2,3,4,5,6]")).print()
}