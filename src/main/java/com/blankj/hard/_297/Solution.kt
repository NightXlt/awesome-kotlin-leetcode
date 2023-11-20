package com.blankj.hard._297

import com.blankj.structure.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque

class Solution {
    // Serialize a BiTree to a String
    fun serialize(root: TreeNode?): String {
        root ?: return "[]"
        val res = StringBuilder("[")
        val queue = ArrayDeque<TreeNode?>().apply { add(root) }
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
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
        val queue = ArrayDeque<TreeNode?>().apply { add(root) }
        var i = 1
        while (queue.isNotEmpty() && i < nodes.size) {
            val node = queue.removeFirst()
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
        queue.clear()
        return root
    }
}