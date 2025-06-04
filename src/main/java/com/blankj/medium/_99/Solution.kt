package com.blankj.medium._99

import com.blankj.structure.TreeNode
import java.util.*

class Solution {
    fun recoverTree(root: TreeNode?) {
        var root = root
        val stack: Deque<TreeNode> = ArrayDeque<TreeNode>()
        var pred: TreeNode? = null
        var errorNode1: TreeNode? = null
        var errorNode2: TreeNode? = null
        while (stack.isNotEmpty() || root != null) {
            while (root != null) {
                stack.push(root)
                root = root.left
            }
            root = stack.pop()
            if (pred != null && root!!.`val` < pred.`val`) {
                errorNode2 = root
                if (errorNode1 == null) {
                    errorNode1 = pred
                } else {
                    break
                }
            }
            pred = root
            root = root?.right
        }
        errorNode1?.`val` = errorNode2?.`val`?.also { errorNode2.`val` = errorNode1!!.`val` } ?: 0
    }

}

fun main() {
    Solution().recoverTree(TreeNode.createTestData("[1,3,null,null,2]"))
    Solution().recoverTree(TreeNode.commonBSTTree())
    Solution().recoverTree(TreeNode.commonBSTTree())
    Solution().recoverTree(TreeNode.commonBSTTree())
}