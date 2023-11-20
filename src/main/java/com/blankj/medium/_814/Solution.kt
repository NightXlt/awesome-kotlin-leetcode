package com.blankj.medium._814

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import com.sun.source.tree.Tree

class Solution {
    fun pruneTree(root: TreeNode?): TreeNode? {
        root ?: return null
        root.left = pruneTree(root.left)
        root.right = pruneTree(root.right)
        if (root.left == null && root.right == null &&
            root.`val` == 0
        ) {
            return null
        }
        return root
    }
}

fun main() {
    Solution().pruneTree(
        TreeNode.createTestData("[1,null,0,null,null,0,1]")
    )?.print()
}