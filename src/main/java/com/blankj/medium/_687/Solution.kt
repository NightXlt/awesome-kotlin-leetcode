package com.blankj.medium._687

import com.blankj.structure.TreeNode
import kotlin.math.max

class Solution {
    var res = 0
    fun longestUnivaluePath(root: TreeNode?): Int {
        res = 0
        postOrder(root)
        return res
    }

    private fun postOrder(root: TreeNode?): Int {
        root ?: return 0
        val leftLength = postOrder(root.left)
        val rightLength = postOrder(root.left)
        var curLeftLength = 0
        var curRightLength = 0
        if (root.left != null && root.left.`val` == root.`val`) {
            curLeftLength = leftLength + 1
        }
        if (root.right != null && root.right.`val` == root.`val`) {
            curRightLength = rightLength + 1
        }
        res = max(res, curLeftLength + curRightLength)
        return max(curLeftLength, curRightLength)
    }
}