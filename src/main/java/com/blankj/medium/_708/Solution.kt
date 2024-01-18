package com.blankj.medium._708

class Node(var `val`: Int) {
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
                cur = node
            }
            cur.next = dummy.next
            return dummy.next
        }
    }
}


class Solution {
    fun insert(head: Node?, insertVal: Int): Node? {
        when {
            head == null -> {
                val cur = Node(insertVal)
                cur.next = cur
                return cur
            }

            head.next == head -> {
                val cur = Node(insertVal)
                head.next = cur
                cur.next = head
                return head
            }

            else -> {
                insertCore(head, insertVal)
                return head
            }
        }

    }

    private fun insertCore(head: Node?, insertVal: Int) {
        var cur = head
        var biggestNode = head
        while (!(cur?.`val`.orEmpty() <= insertVal && cur?.next?.`val`.orEmpty() > insertVal) && cur?.next != head) {
            cur = cur?.next
            if (cur?.`val`.orEmpty() >= biggestNode?.`val`.orEmpty() && cur?.next?.`val`.orEmpty() < cur?.`val`.orEmpty() ) {
                biggestNode = cur
            }
        }
        val node = Node(insertVal)
        if (cur?.`val`.orEmpty() <= insertVal && cur?.next?.`val`.orEmpty() > insertVal) {
            node.next = cur?.next
            cur?.next = node
        } else {
            node.next = biggestNode?.next
            biggestNode?.next = node
        }
    }
}

fun Int?.orEmpty(): Int {
    return this ?: 0
}

fun main() {
    Solution().insert(
        Node.createTestData("[3,1,3]"),
        4
    )
}