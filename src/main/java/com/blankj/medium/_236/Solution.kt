package com.blankj.medium._236

import com.blankj.structure.TreeNode
import kotlin.math.min


class Solution {
    private fun getPath(root: TreeNode, target: TreeNode, path: MutableList<TreeNode?>): Boolean {
        path.add(root)
        if (root.`val` == target.`val`) {
            return true
        }
        var found = false
        if (root.left != null) found = getPath(root.left, target, path)
        if (!found && root.right != null) found = getPath(root.right, target, path)
        if (!found) path.remove(root)
        return found
    }

    private fun getLastAncestor(listP: List<TreeNode?>, listQ: List<TreeNode?>): TreeNode? {
        var pNode: TreeNode?
        var qNode: TreeNode? = null
        for (i in (min(listP.lastIndex, listQ.lastIndex)) downTo 0) {
            pNode = listP[i]
            qNode = listQ[i]
            if (pNode == qNode) {
                return pNode
            }
        }
        return qNode
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null || p == null || q == null) return null
        val listP: MutableList<TreeNode?> = mutableListOf()
        val listQ: MutableList<TreeNode?> = mutableListOf()
        getPath(root, p, listP)
        getPath(root, q, listQ)
        return getLastAncestor(listP, listQ)
    }
}