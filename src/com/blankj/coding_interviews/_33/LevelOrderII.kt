package com.blankj.coding_interviews._33

import com.blankj.coding_interviews._004.print
import com.blankj.structure.TreeNode
import java.util.*


class Solution {

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        root ?: return listOf()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        val result = mutableListOf<MutableList<Int>>()
        while (queue.isNotEmpty()) {
            val temp = mutableListOf<Int>()
            repeat(queue.size) {
                val node = queue.poll()
                temp.add(node.`val`)
                if (node.left != null) {
                    queue.add(node.left)
                }
                if (node.right != null) {
                    queue.add(node.right)
                }
            }
            result.add(temp)
        }
        return result
    }
}

fun main() {
    Solution().levelOrder(null).print()
    Solution().levelOrder(TreeNode.createTestData("[8,6,10,5,7,9,11]")).print()
    Solution().levelOrder(TreeNode.createTestData("[8,6,null,5,null]")).print()
    Solution().levelOrder(TreeNode.createTestData("[8,null,6,null,null,null,5]")).print()
    Solution().levelOrder(TreeNode(8)).print()
}
