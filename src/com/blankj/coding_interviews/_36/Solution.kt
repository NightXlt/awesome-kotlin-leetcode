package com.blankj.coding_interviews._36

import com.blankj.structure.TreeNode

class Solution {

    var head: TreeNode? = null
    var pre: TreeNode? = null

    fun treeToDoublyList(root: TreeNode?): TreeNode? {
        if (root == null) return null
        convertNode(root)
        head?.left = pre
        pre?.right = head
        return head
    }

    private fun convertNode(root: TreeNode?) {
        if (root == null) return
        convertNode(root.left)
        if (pre != null) {
            pre?.right = root
            root.left = pre
        } else {
            head = root
        }
        pre = root
        convertNode(root.right)
    }


}

fun main(args: Array<String>) {
    Solution().treeToDoublyList(TreeNode.createTestData("[4,2,5,1,3,null,null]"))
    Solution().treeToDoublyList(TreeNode.createTestData("[5,3,7,2,4,6,8]"))
}