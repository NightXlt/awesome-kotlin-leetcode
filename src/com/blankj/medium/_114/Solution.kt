package com.blankj.medium._114

import com.blankj.structure.TreeNode

class Solution {
    fun flatten(root: TreeNode?): Unit {
        var node = root
        while (node != null) {
            val leftChild = node.left
            if (leftChild == null) {
                node = node.right
                continue
            }
            var pre = leftChild
            while (pre?.right != null) pre = pre.right
            pre?.right = node.right
            node.right = leftChild
            node.left = null
            node = node.right
        }
    }
}