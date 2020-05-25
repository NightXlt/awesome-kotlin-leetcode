package com.blankj.medium._113

import com.blankj.structure.TreeNode
import java.util.*

class Solution {

    lateinit var res: MutableList<List<Int>>

    fun pathSum(root: TreeNode?, sum: Int): List<List<Int>> {
        root ?: return emptyList()
        res = mutableListOf()
        val list = ArrayDeque<Int>()
        list.add(root.`val`)
        dfs(root, sum - root.`val`, list)
        return res
    }

    private fun dfs(root: TreeNode, sum: Int, list: ArrayDeque<Int>) {
        if (isLeaf(root)) {
            if (sum == 0) {
                res.add(list.toList())
            }
            return
        }
        visitNode(root.left, list, sum)
        visitNode(root.right, list, sum)
    }

    private fun visitNode(root: TreeNode?, list: ArrayDeque<Int>, sum: Int) {
        root?.run {
            list.add(`val`)
            dfs(this, sum - `val`, list)
            list.removeLast()
        }
    }

    private fun isLeaf(root: TreeNode) = root.left == null && root.right == null
}

fun main() {
    val root = TreeNode(5)
    root.left = TreeNode(4)
    root.left?.left = TreeNode(11)
    root.left?.left?.left = TreeNode(7)
    root.left?.left?.right = TreeNode(2)
    root.right = TreeNode(8)
    root.right?.left = TreeNode(13)
    root.right?.right = TreeNode(4)
    root.right?.right?.left = TreeNode(5)
    root.right?.right?.right = TreeNode(1)
    Solution().pathSum(root, 22)
}