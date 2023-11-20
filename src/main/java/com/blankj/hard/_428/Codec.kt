package com.blankj.hard._428

import com.blankj.ext.print

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
        root ?: return
        val (value, children) = root.`val` to root.children
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
        val queue: ArrayDeque<String> = ArrayDeque()
        data.splitToSequence(',')
            .toCollection(queue)
        return buildTree(queue)
    }

    private fun buildTree(queue: ArrayDeque<String>): Node {
        val value = queue.removeFirst().toInt()
        val childrenSize = queue.removeFirst().toInt()
        val root = Node(value)
        val children = mutableListOf<Node>()
        repeat(childrenSize) {
            children.add(buildTree(queue))
        }
        root.children = children
        return root
    }
}

class Node(
    val `val`: Int
) {
    var children: List<Node>? = null
}

fun main() {
    Codec().deserialize("1,3,2,0,3,0,4,0")
    Node(1).apply {
        this.children = listOf(
            Node(2),
            Node(3),
            Node(4)
        )
        Codec().serialize(this).print()
    }
}