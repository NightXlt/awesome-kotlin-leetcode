package com.blankj.medium._430

class Node(var `val`: Int) {
    var prev: Node? = null
    var next: Node? = null
    var child: Node? = null

    companion object {
        fun createTestData(str: String): Node? {
            val data = str.trim('[', ']', ' ').split(',')
            val dummy = Node(0)
            var cur = dummy
            for (value in data) {
                if (value == "null") break
                val node = Node(value.toInt())
                cur.next = node
                node.prev = cur
                cur = node
            }
            dummy.next?.prev = null
            return dummy.next
        }
    }
}


class Solution {
    fun flatten(root: Node?): Node? {
        if (root == null) return root
        flattenReturnTail(root)
        return root
    }

    private fun flattenReturnTail(root: Node?): Node? {
        var node = root
        var tail: Node? = null
        while (node != null) {
            val next = node.next
            if (node.child != null) {
                val child = node.child
                val childTail = flattenReturnTail(child)
                node.child = null
                node.next = child
                child?.prev = node
                childTail?.next = next
                if (next != null) {
                    next.prev = childTail
                }
                tail = childTail
            } else {
                tail = node
            }
            node = node.next
        }
        return tail
    }
}

fun main() {
    val firstLinkedList = Node.createTestData("[1,2,3,4,5,6]")
    val secondLinkedList = Node.createTestData("[7,8,9,10]")
    val thirdLinkedList = Node.createTestData("[11,12]")
    firstLinkedList?.next?.next?.child = secondLinkedList
    secondLinkedList?.next?.child = thirdLinkedList
    Solution().flatten(firstLinkedList)
}