package com.blankj.google

import com.blankj.ext.print
import com.blankj.structure.TreeNode
import java.lang.Integer.max

class TreeQueries {
    var res: IntArray = intArrayOf()
    val heightStore = mutableMapOf<TreeNode?, Int>()
    fun treeQueries(root: TreeNode?, queries: IntArray): IntArray {
        if (root == null) error("tree is empty!")
        if (queries.isEmpty()) error("queries is empty!")
        getHeight(root)
        heightStore[null] = 0
        res = IntArray(heightStore.size)
        preorder(root, -1, 0)
        queries.forEachIndexed { index, value ->
            if (value !in res.indices) error("value is bigger than nodes size")
            queries[index] = res[value]
        }
        return queries
    }

    private fun preorder(
        root: TreeNode?,
        depth: Int,
        restH: Int
    ) {
        root ?: return
        res[root.`val`] = restH
        val dep = depth + 1
        preorder(root.left, dep, max(restH, dep + heightStore.getValue(root.right)))
        preorder(root.right, dep, max(restH, dep + heightStore.getValue(root.left)))
    }

    private fun getHeight(root: TreeNode?): Int {
        root ?: return 0
        val maxChildHeight = max(getHeight(root.left), getHeight(root.right))
        val height = maxChildHeight + 1
        heightStore[root] = height
        return height
    }
}

fun main() {
    TreeQueries().treeQueries(
        null,
        intArrayOf(4)
    ).print()
    TreeQueries().treeQueries(
        TreeNode.createTestData("[1,3,4,2,null,6,5,null,null,null,null,null,7]"),
        intArrayOf()
    ).print()
    TreeQueries().treeQueries(
        TreeNode.createTestData("[1,3,4,2,null,6,5,null,null,null,null,null,7]"),
        intArrayOf(4)
    ).print()
}