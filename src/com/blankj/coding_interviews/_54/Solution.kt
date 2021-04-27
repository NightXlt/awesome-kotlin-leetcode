package com.blankj.coding_interviews._54

import com.blankj.coding_interviews._004.print
import com.blankj.structure.TreeNode
import java.util.*
import kotlin.math.max
import kotlin.math.min

class Solution {

    fun kthLargest(root: TreeNode?, k: Int): Int {
        if (root == null || k <= 0) throw IllegalArgumentException("illegal arg")
        var k = k
        val stack = ArrayDeque<TreeNode>()
        var p: TreeNode? = root
        while (p != null || stack.isNotEmpty()) {
            while (p != null) {
                stack.add(p)
                p = p.right
            }
            k--
            p = stack.removeLast()
            if (k == 0) {
                return p.`val`
            }
            p = p.left
        }
        throw IllegalStateException("K is more than tree count of nodes")
    }
}

fun main() {
//    Solution().kthLargest(null, 3).print()
    Solution().kthLargest(TreeNode.createJustLeftChild(), 3).print()
    Solution().kthLargest(TreeNode.createJustRightChild(), 3).print()
    Solution().kthLargest(TreeNode.createTestData("[3,1,4,null,2,null,null]"), 2).print()
    Solution().kthLargest(TreeNode.createTestData("[5,3,6,2,4,null,null,1]"), 3).print()
}