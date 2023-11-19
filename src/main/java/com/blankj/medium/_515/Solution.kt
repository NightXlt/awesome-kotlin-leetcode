package com.blankj.medium._515

import com.blankj.structure.TreeNode
import java.util.*
import kotlin.math.max


class Solution {
    fun largestValues(root: TreeNode?): List<Int> {
        root ?: return emptyList()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        val res = mutableListOf<Int>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            var max = Int.MIN_VALUE
            repeat(size) {

                val node = queue.removeFirst()
                if (node.left != null) {
                    queue.add(node.left)
                }
                if (node.right != null) {
                    queue.add(node.right)
                }
                max = max.coerceAtLeast(node.`val`) // 求 max， 不小于 val
            }
            res.add(max)
        }
        return res
    }
}