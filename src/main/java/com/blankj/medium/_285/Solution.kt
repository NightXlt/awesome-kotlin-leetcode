package com.blankj.medium._285

import com.blankj.structure.TreeNode

class Solution {
    fun inorderSuccessor(root: TreeNode?, p: TreeNode?): TreeNode? {
        if (root == null || p == null) {
            return null
        }
        var cur = root
        var res: TreeNode? = null
        while (cur != null) {
            cur = if (cur.`val` > p.`val`) {
                res = cur
                cur.left
            } else {
                cur.right
            }
        }
        return res
    }
}