package com.blankj.medium._919

import com.blankj.structure.TreeNode


class CBTInserter(val root: TreeNode?) {

    private val queue = ArrayDeque<TreeNode>()

    init {
        var node = root ?: error("Illegal Input: empty root node")
        queue.add(node)
        while (queue.first().left != null && queue.first().right != null) {
            node = queue.removeFirst()
            queue.add(node.left)
            queue.add(node.right)
        }
    }

    fun insert(`val`: Int): Int {
        val parent = queue.first()
        val node = TreeNode(`val`)
        if (parent.left == null) {
            parent.left = node
        } else {
            parent.right = node
            queue.removeFirst()
            queue.add(parent.left)
            queue.add(parent.right)
        }
        return parent.`val`
    }

    fun get_root(): TreeNode? {
        return root
    }

}

fun main() {
    val cbtInserter = CBTInserter(
        TreeNode.createTestData("[1,2,3,4,5,6]")
    )
    cbtInserter.insert(7)
    cbtInserter.insert(8)
}