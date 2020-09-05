package com.blankj.hard._297

import com.blankj.structure.TreeNode
import java.util.*

class Solution {
    // Serialize a BiTree to a String
    fun serialize(root: TreeNode?): String {
        root ?: return "[]"
        val res = StringBuilder("[")
        val queue = LinkedList<TreeNode?>().apply { add(root) }
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            if (node == null) {
                res.append("null,")
                continue
            }
            res.append("${node.`val`},")
            queue.add(node.left)
            queue.add(node.right)
        }
        res.deleteCharAt(res.lastIndex)
        res.append("]")
        return res.toString()
    }

    // Deserialize string to BiTree
    fun deserialize(data: String): TreeNode? {
        if (data == "[]") return null
        val nodes = data.substring(1, data.lastIndex).split(',')
        val root = TreeNode(nodes[0].toInt())
        val queue = LinkedList<TreeNode?>().apply { add(root) }
        var i = 1
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            if (nodes[i] != "null") {
                node?.left = TreeNode(nodes[i].toInt())
                queue.add(node?.left)
            }
            i++
            if (nodes[i] != "null") {
                node?.right = TreeNode(nodes[i].toInt())
                queue.add(node?.right)
            }
            i++
        }
        return root
    }
}