package com.blankj.medium._653

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

    fun findTargetWithBSTFeature(root: TreeNode?, k: Int): Boolean {
        root ?: return false
        val iterNext = BSTIterator(root)
        val iterPrev = BSTIteratorReversed(root)
        var next = iterNext.next()
        var prev = iterPrev.prev()
        while (next != prev) {
            if (next + prev == k) {
                return true
            }
            if (next + prev < k) {
                next = iterNext.next()
            } else {
                prev = iterPrev.prev()
            }
        }
        return false
    }
}

class BSTIterator(root: TreeNode?) {

    var cur = root
    val stack = ArrayDeque<TreeNode>()

    fun next(): Int {
        while (cur != null) {
            stack.add(cur!!)
            cur = cur?.left
        }
        cur = stack.removeLast()
        val res = cur?.`val`
        cur = cur?.right
        return res ?: error("Stack is empty!!!")
    }

}


class BSTIteratorReversed(root: TreeNode?) {

    var cur = root
    val stack = ArrayDeque<TreeNode>()

    fun prev(): Int {
        while (cur != null) {
            stack.add(cur!!)
            cur = cur?.right
        }
        cur = stack.removeLast()
        val res = cur?.`val`
        cur = cur?.left
        return res ?: error("Stack is empty!!!")
    }

}
