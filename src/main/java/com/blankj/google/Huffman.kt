package com.blankj.google

import com.blankj.ext.print
import java.util.*


class Huffman {
    var c = 0.toChar()
    var data = 0
    var left: Huffman? = null
    var right: Huffman? = null
}


class ConstructHuffman {
    var res = ArrayList<String>()
    fun huffmanCodes(dict: Map<Char, Int>): ArrayList<String> {
        val pq = PriorityQueue { o1: Huffman, o2: Huffman -> o1.data - o2.data }
        for ((key, value) in dict) {
            val h = Huffman()
            h.data = value
            h.c = key
            h.left = null
            h.right = null
            pq.add(h)
        }
        var root: Huffman? = null
        while (pq.size > 1) {
            val temp1 = pq.poll()
            val temp2 = pq.poll()
            val node = Huffman()
            node.data = temp1.data + temp2.data
            node.c = '-'
            node.left = temp1
            node.right = temp2
            root = node
            pq.add(node)
        }
        printHuffman(root, "")
        return res
    }

    private fun printHuffman(root: Huffman?, s: String) {
        if (root == null) return
        if (root.left == null && root.right == null && root.c.isLetter()) {
            res.add(s)
            return
        }
        printHuffman(root.left, s + "0")
        printHuffman(root.right, s + "1")
    }
}

fun main() {
    ConstructHuffman().huffmanCodes(
        mapOf(
            'a' to 2,
            'b' to 3,
            'c' to 4,
            'd' to 5,
        )
    ).print()
}