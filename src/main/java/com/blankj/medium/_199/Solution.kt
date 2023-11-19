package com.blankj.medium._199

import com.blankj.ext.print
import com.blankj.structure.ListNode
import com.blankj.structure.TreeNode
import java.util.ArrayDeque

class Solution {
    fun rightSideView(root: TreeNode?): List<Int> {
        root ?: return emptyList()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        val res = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            var node: TreeNode? = null
            repeat(size) {
                node = queue.removeFirst()
                node?.left?.run { queue.add(this) }
                node?.right?.run { queue.add(this) }
            }
            res.add(node?.`val` ?: -1)
        }
        return res
    }
}