package com.blankj.medium._1448

import com.blankj.ext.print
import com.blankj.structure.TreeNode

class Solution {
    var count = 0
    fun goodNodes(root: TreeNode?): Int {
        preorderTraversal(root, Int.MIN_VALUE)
        return count
    }

    fun preorderTraversal(root: TreeNode?, max: Int) {
        if (root == null) return
        val max = if (root.`val` >= max) {
            count++
            root.`val`
        } else max
        preorderTraversal(root.left, max)
        preorderTraversal(root.right, max)
    }
}


fun main() {
    Solution().goodNodes(null).print()
    Solution().goodNodes(TreeNode.oneNode()).print()
    Solution().goodNodes(TreeNode.createJustLeftChild()).print()
    Solution().goodNodes(TreeNode.createJustRightChild()).print()
    Solution().goodNodes(TreeNode.commonTree()).print()
    Solution().goodNodes(TreeNode.createTestData("[3,1,4,3,null,1,5,null,null]")).print()
}