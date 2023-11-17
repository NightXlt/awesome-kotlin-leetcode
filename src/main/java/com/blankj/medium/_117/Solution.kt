package com.blankj.medium._117


class Solution {
    private var previousNode: Node? = null
    private var nextLayerStartNode: Node? = null

    fun connect(root: Node?): Node? {
        if (root == null) return root
        var start = root
        while (start != null) {
            previousNode = null
            nextLayerStartNode = null
            var p = start
            while (p != null) {
                if (p.left != null) {
                    handle(p.left)
                }
                if (p.right != null) {
                    handle(p.right)
                }
                p = p.next
            }
            start = nextLayerStartNode
        }
        return root
    }

    private fun handle(p: Node?) {
        previousNode?.next = p
        if (nextLayerStartNode == null) {
            nextLayerStartNode = p
        }
        previousNode = p
    }
}

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

fun main() {
    // [-9,-3,2,null,4,4,0,-6,null,-5]
    val node = Node(-9)
            .apply {
                left = Node(-3).apply {
                    right = Node(4).apply {
                        left = Node(-6)
                    }
                }
                right = Node(2).apply {
                    left = Node(4).apply {
                        left = Node(-5)
                    }
                    right = Node(0)
                }
            }
    Solution().connect(node)
}