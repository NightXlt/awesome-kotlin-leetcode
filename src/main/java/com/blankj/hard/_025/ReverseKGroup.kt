package com.blankj.hard._025

import com.blankj.ext.print
import com.blankj.structure.ListNode

class ReverseKGroup {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null || k == 1) return head
        val dummyHead = ListNode(0)
        var tail = head
        var pre: ListNode? = dummyHead
        dummyHead.next = head
        while (tail != null) {
            repeat(k - 1) {
                tail = tail?.next ?: return dummyHead.next
            }
            val nextHead = pre?.next
            reverseNode(nextHead, tail)
            pre?.next = tail
            pre = nextHead
            // Recall to move tail to next tail node
            tail = nextHead?.next
        }
        return dummyHead.next
    }

    private fun reverseNode(head: ListNode?, tail: ListNode?) {
        var preNode: ListNode? = tail?.next
        var curNode = head
        while (preNode != tail) {
            val nextNode = curNode?.next
            curNode?.next = preNode
            preNode = curNode
            curNode = nextNode
        }
    }
}

fun main() {
    ReverseKGroup().reverseKGroup(ListNode.testCase0(), 3)?.print()
    ReverseKGroup().reverseKGroup(ListNode.testCase1(), 3)?.print()
    ReverseKGroup().reverseKGroup(ListNode.testCase2(), 3)?.print()
    ReverseKGroup().reverseKGroup(ListNode.testCase3(), 3)?.print()
    ReverseKGroup().reverseKGroup(ListNode.testCase3(), 1)?.print()
}