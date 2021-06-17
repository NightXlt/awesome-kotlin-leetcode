package com.blankj.coding_interviews._27

import com.blankj.coding_interviews._004.print
import com.blankj.structure.TreeNode
import java.util.*


class Solution {
    //    fun mirrorTree(root: TreeNode?): TreeNode? {
//        root ?: return root
//        root.left = root.right.also {
//            root.right = root.left
//        }
//        root.left.apply {
//            mirrorTree(this)
//        }
//        root.right.apply {
//            mirrorTree(this)
//        }
//        return root
//    }
    fun mirrorTree(root: TreeNode?): TreeNode? {
        root ?: return root
        val stack = ArrayDeque<TreeNode?>()
        stack.add(root)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            node?.left?.apply { stack.add(this) }
            node?.right?.apply { stack.add(this) }
            root.left = root.right.also {
                root.right = root.left
            }
        }
        return root
    }
}

fun main() {
    Solution().mirrorTree(null)?.print()
    Solution().mirrorTree(TreeNode(2))?.print()
    Solution().mirrorTree(TreeNode.createTestData("[1,2,2,null,3,null,3]"))?.print() // normal case
    Solution().mirrorTree(TreeNode.createTestData("[1,2,null,2,null,2,null]"))?.print()// just left child
    Solution().mirrorTree(TreeNode.createTestData("[1,null,2,null,2,null,2]"))?.print()// just right child
}
