package com.blankj.medium._513

import com.blankj.structure.TreeNode
import java.lang.IllegalArgumentException
import java.util.*

class Solution {

    private var leftestValue = -1
    var maxDepth = 0

    fun findBottomLeftValue(root: TreeNode?): Int {
        dfs(root, 1)
        return leftestValue
    }

    fun dfs(root: TreeNode?, depth: Int) {
        val root = root ?: return
        if (depth > maxDepth) {
            maxDepth = depth
            leftestValue = root.`val`
        }
        dfs(root.left, depth + 1)
        dfs(root.right, depth + 1)
    }

    fun findBottomLeftValueByBFS(root: TreeNode?): Int {
        val root = root ?: throw IllegalArgumentException("root must be not null")
        var queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var result = root
        while (queue.isNotEmpty()) {
            result = queue.removeFirst()
            result.right?.run { queue.add(this) }
            result.left?.run { queue.add(this) }
        }
        return result.`val`
    }

}

fun main() {
    Solution().findBottomLeftValueByBFS(TreeNode(2)?.apply {
        left = TreeNode(1)
        right = TreeNode(3)
    })
}