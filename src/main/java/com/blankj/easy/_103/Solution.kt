package com.blankj.easy._103

import com.blankj.structure.TreeNode
import kotlin.collections.ArrayDeque

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution() {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        root ?: return emptyList()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var isReversed = false
        val res = mutableListOf<List<Int>>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            val currentLayer = mutableListOf<Int>()
            repeat(size) {
                val cur = queue.removeFirst()
                cur.left?.apply { queue.add(this) }
                cur.right?.apply { queue.add(this) }
                if (!isReversed) {
                    currentLayer.add(cur.`val`)
                } else {
                    currentLayer.add(0, cur.`val`)
                }
            }
            isReversed = !isReversed
            res.add(currentLayer)
        }
        return res
    }
}
