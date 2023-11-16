package com.blankj.medium._143

import com.blankj.ext.print
import com.blankj.structure.ListNode

class ReorderList {

    fun reorderList(head: ListNode?): Unit {
        head ?: return
        val middleNode = findMiddleNode(head)
        val nextStart = middleNode.next
        middleNode.next = null
        mergeTwoList(head, reverseList(nextStart))
    }

    private fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var cur: ListNode? = head
        while (cur != null) {
            val next = cur.next
            cur.next = prev
            prev = cur
            cur = next
        }
        return prev
    }

    private fun mergeTwoList(head: ListNode, nextListHead: ListNode?) {
        var firstNode: ListNode? = head
        var secondNode = nextListHead
        while (firstNode != null && secondNode != null) {
            val nextTemp1 = firstNode.next
            val nextTemp2 = secondNode.next
            firstNode.next = secondNode
            secondNode.next = nextTemp1
            firstNode = nextTemp1
            secondNode = nextTemp2
        }
    }

    private fun findMiddleNode(head: ListNode): ListNode {
        var fast = head
        var slow = head
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next
            slow = slow.next
        }
        return slow
    }
}

fun main() {
    ReorderList().reorderList(ListNode.createTestData("[1,2,3,4]")).print()
    ReorderList().reorderList(ListNode.createTestData("[1,2,3,4,5]")).print()
    ReorderList().reorderList(ListNode.createTestData("[1]")).print()
}