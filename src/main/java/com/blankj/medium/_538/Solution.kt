package com.blankj.medium._538

import com.blankj.structure.TreeNode

class Solution {
    fun convertBST(root: TreeNode?): TreeNode? {
        root ?: return null
        var cur = root
        var stack = ArrayDeque<TreeNode>()
        var sum = 0
        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
                stack.add(cur)
                cur = cur.right
            }
            cur = stack.removeLast()
            sum += cur.`val` // remember to accumulate independently
            cur.`val` = sum
            cur = cur.left
        }
        return root
    }
}