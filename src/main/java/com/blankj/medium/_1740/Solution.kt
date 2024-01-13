package com.blankj.medium._1740

import com.blankj.structure.TreeNode
import kotlin.math.min


class Solution {
    private fun getPath(root: TreeNode, target: Int, path: MutableList<TreeNode?>): Boolean {
        path.add(root)
        if (root.`val` == target) {
            return true
        }
        var found = false
        if (root.left != null) found = getPath(root.left, target, path)
        if (!found && root.right != null) found = getPath(root.right, target, path)
        if (!found) path.remove(root)
        return found
    }

    private fun getLastAncestorPath(listP: List<TreeNode?>, listQ: List<TreeNode?>): Int {
        var pNode: TreeNode?
        var qNode: TreeNode? = null
        for (i in (min(listP.lastIndex, listQ.lastIndex)) downTo 0) {
            pNode = listP[i]
            qNode = listQ[i]
            if (pNode == qNode) {
                return listP.lastIndex - i + listQ.lastIndex - i
            }
        }
        return 0
    }

    fun findDistance(root: TreeNode?, p: Int, q: Int): Int {
        if (root == null) return 0
        var listP: MutableList<TreeNode?> = mutableListOf()
        var listQ: MutableList<TreeNode?> = mutableListOf()
        getPath(root, p, listP)
        getPath(root, q, listQ)
        return getLastAncestorPath(listP, listQ)
    }
}