package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.TreeNode

class DelNodes {
    private var deleteSet = setOf<Int>()
    private var res = mutableListOf<TreeNode?>()
    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        deleteSet = to_delete.toSet()
        res = mutableListOf()
        postOrder(root, true)
        return res
    }

    private fun postOrder(root: TreeNode?, isRoot: Boolean): TreeNode? {
        root ?: return null
        val isDeleted = root.`val` in deleteSet
        root.left = postOrder(root.left, isDeleted)
        root.right = postOrder(root.right, isDeleted)
        if (isDeleted) {
            return null
        }
        if (isRoot) {
            res.add(root)
        }
        return root
    }
}

fun main() {
    DelNodes().delNodes(
        TreeNode.createTestData("[1,2,3,4,5,6,7]"),
        intArrayOf(3,5)
    ).print()
}