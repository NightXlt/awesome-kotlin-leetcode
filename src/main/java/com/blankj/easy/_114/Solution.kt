package com.blankj.easy._114

import com.blankj.ext.print
import com.blankj.structure.TreeNode


class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        root ?: return emptyList()
        val stack = ArrayDeque<TreeNode>()
        stack.add(root)
        val res = mutableListOf<Int>()
        var cur = root
        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
               res.add(cur.`val`)
                stack.add(cur)
                cur = cur.left
            }
            cur = stack.removeLast().right
        }
        return res
    }
}

fun main() {
    Solution().preorderTraversal(
        TreeNode.createTestData("[1,null,2,3]")
    ).print()
    Solution().preorderTraversal(
        TreeNode.createTestData("[1,null,null]")
    ).print()

}