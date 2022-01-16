package com.blankj.medium._145

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import java.util.*


class Solution {

    fun postorderTraversal(root: TreeNode?): List<Int> {
        var p: TreeNode? = root
        val stack = ArrayDeque<TreeNode>()
        val result = mutableListOf<Int>()
        var pre: TreeNode? = null
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.push(p)
                p = p.left
            }
            p = stack.peek()
            val rightChild = p.right
            if (rightChild == null || rightChild == pre) { // 没有右子树/访问过右子树了，把根节点加到结果集里
                result.add(p.`val`)
                pre = p
                p = null // 必须要置空，避免重复访问p的右子树
                stack.pop()
            } else {
                p = rightChild
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
