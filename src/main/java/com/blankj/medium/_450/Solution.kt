package com.blankj.medium._450

import com.blankj.structure.TreeNode

class Solution {
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null
        var root = root
        when {
            key > root.`val` -> root.right = deleteNode(root.right, key)
            key < root.`val` -> root.left = deleteNode(root.left, key)
            else -> {
                when {
                    root.left == null && root.right == null -> {
                        root = null
                    }
                    // update val to next/pre val (must be leaf node), and remove leaf node of val
                    root.left == null -> {
                        root.`val` = successor(root)
                        root.right = deleteNode(root.right, root.`val`)
                    }
                    else -> {
                        root.`val` = predecessor(root)
                        root.left = deleteNode(root.left, root.`val`)
                    }
                }
            }
        }
        return root
    }

    // find in-order next node of root
    private fun successor(root: TreeNode): Int {
        var rightChild = root.right
        while (rightChild?.left != null) {
            rightChild = rightChild.left
        }
        return rightChild?.`val` ?: 0
    }

    // find in-order previous node of root
    private fun predecessor(root: TreeNode): Int {
        var leftChild = root.left
        while (leftChild?.right != null) {
            leftChild = leftChild.right
        }
        return leftChild?.`val` ?: 0
    }
}

fun main() {
    val deleteNode = Solution().deleteNode(TreeNode.createTestData("[5,3,6,2,4,null,7,null,null,null,null,null,null]"), 3)
    deleteNode
}
