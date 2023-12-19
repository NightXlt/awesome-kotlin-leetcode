package com.blankj.medium._366

import com.blankj.structure.TreeNode
import kotlin.math.max

class Solution {
    var res = mutableListOf<MutableList<Int>>()
    fun findLeaves(root: TreeNode?): List<List<Int>> {
        root ?: return emptyList()
        res = mutableListOf()
        getHeight(root)
        return res
    }

    private fun getHeight(root: TreeNode?) : Int {
        root ?: return -1
        val leftHeight = getHeight(root.left)
        val rightHeight = getHeight(root.right)
        val height = max(leftHeight, rightHeight) + 1
        if (res.size == height) {
            res.add(mutableListOf())
        }
        res[height].add(root.`val`)
        return height
    }
}