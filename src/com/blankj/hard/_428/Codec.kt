package com.blankj.hard._428

import com.blankj.ext.print
import java.util.*

class Codec {
    // Serialize a BiTree to a String
    fun serialize(root: Node?): String {
        root ?: return ""
        val res = StringBuilder("")
        buildString(root, res)
        // remove last 「,」
        res.deleteCharAt(res.lastIndex)
        return res.toString()
    }

    private fun buildString(root: Node?, res: StringBuilder) {
        val (value, children) = root ?: return
        res.append(value)
        res.append(',')
        res.append(children?.size ?: 0)
        res.append(',')
        children?.forEach {
            buildString(it, res)
        }
    }

    // Deserialize string to BiTree
    fun deserialize(data: String?): Node? {
        if (data.isNullOrEmpty()) return null
        val queue: Queue<String> = LinkedList()
        data.splitToSequence(',')
            .toCollection(queue)
        return buildTree(queue)
    }

    private fun buildTree(queue: Queue<String>): Node {
        val value = queue.poll().toInt()
        val childrenSize = queue.poll().toInt()
        val root = Node(value)
        root.children = mutableListOf()
        repeat(childrenSize) {
            root.children?.add(buildTree(queue))
        }
        return root
    }
}

data class Node(
    val value: Int,
    var children: MutableList<Node>? = null
)

fun main() {
    Codec().deserialize("1,2,2,0,3,0")
    Node(1).apply {
        children = mutableListOf()
        children?.add(Node(2))
        children?.add(Node(3))
        Codec().serialize(this).print()
    }
}