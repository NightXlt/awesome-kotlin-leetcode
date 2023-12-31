package com.blankj.easy._637

import com.blankj.ext.print
import com.blankj.structure.TreeNode

class Solution {
    fun averageOfLevels(root: TreeNode?): DoubleArray {
        root ?: return doubleArrayOf()
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        val res = mutableListOf<Double>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            var sum = 0L
            repeat(size) {
                val cur = queue.removeFirst()
                sum += cur.`val`
                cur.left?.apply { queue.add(this) }
                cur.right?.apply { queue.add(this) }
            }
            res.add(sum / size.toDouble())
        }
        return res.toDoubleArray()
    }
}