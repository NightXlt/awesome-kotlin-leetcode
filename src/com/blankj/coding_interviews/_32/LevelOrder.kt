package com.blankj.coding_interviews._32

import com.blankj.coding_interviews._004.print
import com.blankj.structure.TreeNode
import java.util.*


class Solution {

    fun levelOrder(root: TreeNode?): IntArray {
        root ?: return intArrayOf()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        val result = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            result.add(node.`val`)
            if (node.left != null) {
                queue.add(node.left)
            }
            if (node.right != null) {
                queue.add(node.right)
            }
        }
        return result.toIntArray()
    }
}

fun main() {
    Solution().levelOrder(null).print()
    Solution().levelOrder(TreeNode.createTestData("[8,6,10,5,7,9,11]")).print()
    Solution().levelOrder(TreeNode.createTestData("[8,6,null,5,null]")).print()
    Solution().levelOrder(TreeNode.createTestData("[8,null,6,null,null,null,5]")).print()
    Solution().levelOrder(TreeNode(8)).print()
}
