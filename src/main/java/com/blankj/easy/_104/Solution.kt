package com.blankj.easy._104

import com.blankj.structure.TreeNode
import kotlin.math.max

/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2017/10/09
 * desc  :
</pre> *
 */
class Solution {
    fun maxDepth(root: TreeNode?): Int {
        return if (root == null) 0 else (1 + max(
            maxDepth(root.left),
            maxDepth(root.right)
        ))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()
            println(solution.maxDepth(TreeNode.createTestData("[]")))
            println(solution.maxDepth(TreeNode.createTestData("[1,2,2,3,4,4,3]")))
            println(solution.maxDepth(TreeNode.createTestData("[9,-42,-42,null,76,76,null,null,13,null,13]")))
        }
    }
}
