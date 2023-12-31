package com.blankj.easy._112

import com.blankj.structure.TreeNode

class HasPathSum {
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        root ?: return false
        if (root.left == null && root.right == null) return targetSum == root.`val`
        return hasPathSum(root.left, targetSum - root.`val`) || hasPathSum(root.right, targetSum - root.`val`)
    }
}