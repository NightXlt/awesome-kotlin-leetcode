package com.blankj.coding_interviews._28

import com.blankj.coding_interviews._004.print
import com.blankj.structure.TreeNode


class Solution {

    fun isSymmetric(root: TreeNode?): Boolean {
        root ?: return true
        return isSymmetric(root.left, root.right)
    }

    private fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null || left.`val` != right.`val`) return false
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left)
    }
}

fun main() {
    Solution().isSymmetric(null).print()
    Solution().isSymmetric(TreeNode(2)).print()
    Solution().isSymmetric(TreeNode.createTestData("[1,2,2,3,4,4,3]")).print() // normal case
    Solution().isSymmetric(TreeNode.createTestData("[1,2,null,2,null,2,null]")).print()// just left child
    Solution().isSymmetric(TreeNode.createTestData("[1,null,2,null,2,null,2]")).print()// just right child
}
