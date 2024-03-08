package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import kotlin.math.max

class CountIslandWithTree {
    fun countIslands(root: TreeNode?): Int {
        var count = 0
        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0
            val left = dfs(node.left)
            val right = dfs(node.right)
            // 如果当前节点为 1 且左右子节点都为 0，则表示找到一个新的岛屿
            if (node.`val` == 1 && left == 0 && right == 0) {
                count++
            }
            if (node.`val` == 1 && left == 1 && right == 1) {
                count--
            }
            // 返回当前节点及其左右子节点组成的岛屿数量
            return  node.`val`
        }
        dfs(root)
        return count
    }
}

fun main() {
    CountIslandWithTree().countIslands(TreeNode.createTestData("[1,0,0,1,1,1]")).print()
    CountIslandWithTree().countIslands(TreeNode.createTestData("[1,0,1,1,1,1]")).print()
    CountIslandWithTree().countIslands(TreeNode.createTestData("[1,1,1,1,1,1]")).print()
}