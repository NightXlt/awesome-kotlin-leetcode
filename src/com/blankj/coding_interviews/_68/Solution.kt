package com.blankj.coding_interviews._68

class Solution {
    fun nextNode(target: TreeNode?): TreeNode? {
        target ?: return target
        var next: TreeNode? = null
        if (target.rightChild != null) {
            var rightChild = target.rightChild
            while (rightChild?.leftChild != null) {
                rightChild = rightChild.leftChild
            }
            next = rightChild
        } else if (target.parent != null) {
            var cur = target
            var parent = target.parent
            while (parent != null && cur == parent.rightChild) {
                cur = parent
                parent = parent.parent
            }
            next = parent
        }
        return next
    }

    class TreeNode(private val `val`: Int) {

        var leftChild: TreeNode? = null

        var rightChild: TreeNode? = null

        var parent: TreeNode? = null
    }

}

fun main() {
    var target = Solution.TreeNode(2)
    target.leftChild = Solution.TreeNode(1).apply {
        parent = target
    }
    target.rightChild = Solution.TreeNode(3).apply {
        parent = target
    }
    Solution().nextNode(target)
}