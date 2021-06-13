package com.blankj.medium._173

import com.blankj.structure.TreeNode
import java.util.*

class BSTIterator(root: TreeNode?) {

    var cur = root
    val stack: Deque<TreeNode> = ArrayDeque<TreeNode>()

    fun next(): Int {
        while (cur != null) {
            stack.push(cur)
            cur = cur?.left
        }
        cur = stack.pop()
        val res = cur?.`val`
        cur = cur?.right
        return res ?: throw IllegalStateException("Stack is empty!!!")
    }

    fun hasNext(): Boolean = cur != null || stack.isNotEmpty()

}

fun main() {
}