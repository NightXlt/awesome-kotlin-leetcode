package com.blankj.coding_interviews._26

import com.blankj.ext.print
import com.blankj.structure.TreeNode


class Solution {
    fun isSubStructure(s: TreeNode?, t: TreeNode?): Boolean {
        if (s == null || t == null) return false
        return hasSubtree(s, t) || isSubStructure(s.left, t) || isSubStructure(s.right, t)
    }


    private fun hasSubtree(s: TreeNode?, t: TreeNode?): Boolean {
        t ?: return true
        s ?: return false
        return s.`val` == t.`val` &&  hasSubtree(s.left, t.left) && hasSubtree(s.right, t.right)
    }
}

fun main() {
//    Solution().isSubtree(null, null).print()
//    Solution().isSubtree(TreeNode(2), null).print()
//    Solution().isSubtree(TreeNode(2), TreeNode(2)).print()
//    Solution().isSubtree(TreeNode(2), TreeNode(3)).print()
//    Solution().isSubtree(TreeNode.createTestData("[1,2,2,null,3,null,3]"), TreeNode.createTestData("[1,2,2,null,3,3,null]")).print()
//    Solution().isSubtree(TreeNode.createTestData("[1,2,null,2,null,2,null]"), TreeNode.createTestData("[1,2,null,2,null,2,null]")).print()
//    Solution().isSubtree(TreeNode.createTestData("[1,null,2,null,2,null,2]"), TreeNode.createTestData("[1,null,2,null,2,null,2]")).print()
//    Solution().isSubtree(TreeNode.createTestData("[1,2,2,null,3,null,3]"), TreeNode.createTestData("[1,2,2,null,3,null,3]")).print()
      Solution().isSubStructure(TreeNode.createTestData("[10,12,6,8,3,11]"), TreeNode.createTestData("[10,12,6,8]")).print()
}
