package com.blankj.easy._94

import com.blankj.ext.print
import com.blankj.structure.TreeNode


class Solution {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        root ?: return emptyList()
        val stack = ArrayDeque<TreeNode>()
        var cur = root
        val res = mutableListOf<Int>()
        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
                stack.add(cur)
                cur = cur.left
            }
            cur = stack.removeLast()
            res.add(cur.`val`)
            cur = cur.right
        }
        return res
    }
}

fun main() {
    Solution().inorderTraversal(
        TreeNode.createTestData("[1,null,2,null,null,null,3,null,null]")
    ).print()
}