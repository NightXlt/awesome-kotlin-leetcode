package com.blankj.medium._145

import com.blankj.ext.print
import com.blankj.structure.TreeNode


class Solution {

    fun postorderTraversal(root: TreeNode?): List<Int> {
        var cur: TreeNode? = root
        val stack = ArrayDeque<TreeNode>()
        val result = mutableListOf<Int>()
        var pre: TreeNode? = null
        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
                stack.add(cur)
                cur = cur.left
            }
            cur = stack.last()
            // 没有右孩子或者右孩子访问过了,就可以访问根节点了
            if (cur.right == null || cur.right == pre) {
                pre = cur
                result.add(cur.`val`)
                cur = null // 置空防止重复访问
                stack.removeLast()
            } else {
                cur = cur.right
            }
        }
        return result
    }
}

fun main() {
    Solution().postorderTraversal(null).print()
    Solution().postorderTraversal(TreeNode.createTestData("[1,null,2,null,null,3,null]")).print()
    Solution().postorderTraversal(TreeNode.createTestData("[8,6,10,5,7,9,11]")).print()
    Solution().postorderTraversal(TreeNode.createTestData("[8,6,null,5,null]")).print()
    Solution().postorderTraversal(TreeNode.createTestData("[8,null,6,null,null,null,5]")).print()
    Solution().postorderTraversal(TreeNode(8)).print()
}
