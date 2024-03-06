package com.blankj.medium._314

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        root ?: return emptyList()
        val queue = ArrayDeque<Pair<TreeNode, Int>>()
        val columnMap = mutableMapOf<Int, MutableList<Int>>()
        val result = mutableListOf<List<Int>>()
        queue.add(root to 0)
        var minColumn = Int.MAX_VALUE
        var maxColumn = Int.MIN_VALUE
        while (queue.isNotEmpty()) {
            val (node, col) = queue.removeFirst()
            columnMap.getOrPut(col) { mutableListOf() }.add(node.`val`)
            node.left?.apply { queue.add(this to col - 1) }
            node.right?.apply { queue.add(this to col + 1) }
            minColumn = min(minColumn, col)
            maxColumn = max(maxColumn, col)
        }

        for (i in minColumn..maxColumn) {
            result.add(columnMap.getValue(i))
        }
        return result
    }
}

fun main() {
    Solution().verticalOrder(TreeNode.createTestData("[3,9,20,null,null,15,7]")).print()
    Solution().verticalOrder(TreeNode.createTestData("[3,9,8,4,0,1,7,null,null,null,2,5]")).print()
}