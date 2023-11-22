package com.blankj.medium._173

import com.blankj.structure.TreeNode

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

    fun hasNext(): Boolean = cur != null || stack.isNotEmpty()

}

fun main() {
}