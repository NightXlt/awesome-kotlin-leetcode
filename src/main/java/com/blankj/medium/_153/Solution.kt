package com.blankj.medium._153

import com.blankj.structure.TreeNode
import kotlin.collections.ArrayDeque

class Solution {
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        root ?: return false
        val set = mutableSetOf<Int>()
        var cur = root
        val stack = ArrayDeque<TreeNode>()
        while (cur != null || stack.isNotEmpty()) {
            while (cur != null) {
                stack.add(cur)
                cur = cur.left
            }
            cur = stack.removeLast()
            if ((k - cur.`val`) in set) {
                return true
            }
            set.add(cur.`val`)
            cur = cur.right
        }
        return false
    }
}