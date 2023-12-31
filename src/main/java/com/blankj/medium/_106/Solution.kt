package com.blankj.medium._106

import com.blankj.ext.print
import com.blankj.structure.TreeNode

class Solution {
    private var postorder: IntArray = intArrayOf()
    private var inorderMap = mutableMapOf<Int, Int>()
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        if (postorder.isEmpty() || inorder.isEmpty()) return null
        if (postorder.size != inorder.size) error("two order array size should be equal")
        this.postorder = postorder
        inorder.forEachIndexed { index, i -> inorderMap[i] = index }
        return build(0, postorder.size - 1, 0, inorder.size - 1)
    }

    private fun build(
        startPostorder: Int,
        endPostorder: Int,
        startInorder: Int,
        endInorder: Int
    ): TreeNode? {
        val root = TreeNode(postorder[endPostorder])
        val rootInorder = inorderMap[postorder[endPostorder]]
            ?: error("Could not find out postorder number ${postorder[endPostorder]} in inorder")
        val leftChildLen = rootInorder - startInorder
        val rightChildLen = endInorder - rootInorder
        val leftPostorderEnd = startPostorder + leftChildLen - 1
        if (leftChildLen != 0) {
            root.left = build(startPostorder, leftPostorderEnd, startInorder, rootInorder - 1)
        }
        if (rightChildLen != 0) {
            root.right = build(leftPostorderEnd + 1, endPostorder - 1, rootInorder + 1, endInorder)
        }
        return root
    }
}

fun main() {
    Solution().buildTree(
        intArrayOf(9, 3, 15, 20, 7),
        intArrayOf(9, 15, 7, 20, 3),
    )?.print()
}