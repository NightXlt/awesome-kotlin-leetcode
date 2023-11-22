package com.blankj.medium._426

import com.blankj.structure.TreeNode


//class TreeNode(var `val`: Int) {
//    var left: TreeNode? = null
//    var right: TreeNode? = null
//}

class Solution {

    var head: TreeNode? = null
    var pre: TreeNode? = null

    fun treeToList(root:TreeNode?): TreeNode? {
        root ?: return null
        inOrderTraverse(root)
        return head
    }

    private fun inOrderTraverse(root: TreeNode?) {
        root ?: return
        inOrderTraverse(root.left)
        if (pre != null) {
            pre?.right = root
        } else {
            head = root
        }
        pre = root
        root.left = null // remember to update left pointr
        inOrderTraverse(root.right)
    }

    fun treeToDoublyList(root:TreeNode?): TreeNode? {
        root ?: return null
        inOrderTraverseForDouble(root)
        head?.left = pre
        pre?.right = head
        return head
    }
    private fun inOrderTraverseForDouble(root: TreeNode?) {
        root ?: return
        inOrderTraverse(root.left)
        if (pre != null) {
            pre?.right = root
            root.left = pre
        } else {
            head = root
        }
        pre = root
        inOrderTraverse(root.right)
    }

    fun treeToCircularDoublyListTraverse(root:TreeNode?): TreeNode? {
        root ?: return null
        val stack = ArrayDeque<TreeNode>()
        var cur = root
        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
                stack.add(cur)
                cur = cur.left
            }
            cur = stack.removeLast()
            if (pre != null) {
                pre?.right = cur
                cur.left = pre
            } else {
                head = root
            }
            pre = root
            cur = cur.right
        }
        head?.left = pre
        pre?.right = head
        return head
    }
}

fun main() {
    Solution().treeToList(
        TreeNode.commonBSTTree()
    )
}