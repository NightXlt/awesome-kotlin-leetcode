package com.blankj.coding_interviews._007

import com.blankj.coding_interviews._004.print
import com.blankj.structure.TreeNode

class Solution {

    var preOrder: IntArray = intArrayOf()
    var inorderMap = hashMapOf<Int, Int>()

    fun buildTree(preOrder: IntArray, inorder: IntArray): TreeNode? {
        if (preOrder.isEmpty() || inorder.isEmpty()) return null
        if (preOrder.size != inorder.size) throw IllegalArgumentException("two order array size should be equal")
        this.preOrder = preOrder
        inorder.forEachIndexed { index, i -> inorderMap[i] = index }
        return build(0, preOrder.size - 1, 0, inorder.size - 1)
    }

    private fun build(
        startPreorder: Int,
        endPreorder: Int,
        startInorder: Int,
        endInorder: Int
    ): TreeNode? {
        val root = TreeNode(preOrder[startPreorder])
        val rootInorder = inorderMap[preOrder[startPreorder]] // root index in inorder array
            ?: throw IllegalArgumentException("Could not find out preorder number ${preOrder[startPreorder]} in inorder")
        val leftChildLen = rootInorder - startInorder
        val rightChildLen = endInorder - rootInorder
        val leftPreorderEnd = startPreorder + leftChildLen
        if (leftChildLen > 0) {
            root.left = build(startPreorder + 1, leftPreorderEnd, startInorder, rootInorder - 1)
        }
        if (rightChildLen > 0) {
            root.right = build(leftPreorderEnd + 1, endPreorder, rootInorder + 1, endInorder)
        }
        return root
    }
}

fun main() {
    // test cases
    /*  Solution().buildTree(intArrayOf(), intArrayOf())?.print() // empty array
      Solution().buildTree(intArrayOf(0), intArrayOf(2))?.print() // can't find number in in order
      Solution().buildTree(intArrayOf(0), intArrayOf(2, 3))?.print() // size isn't equal
  */
    Solution().buildTree(
        intArrayOf(1, 2, 4, 7, 3, 5, 6, 8),
        intArrayOf(4, 7, 2, 1, 5, 3, 8, 6)
    )?.print() // non-complete binary tree.
    Solution().buildTree(intArrayOf(1, 2, 3), intArrayOf(2, 1, 3))?.print() // complete binary tree.
    Solution().buildTree(intArrayOf(1, 2, 3), intArrayOf(3, 2, 1))?.print() // just left subtree
    Solution().buildTree(intArrayOf(1, 2, 3), intArrayOf(1, 2, 3))?.print() // just right subtree
}