package com.blankj.hard._025

import com.blankj.coding_interviews._004.print
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
            reverseNode(pre, tail)
            pre?.next = tail
            pre = nextHead
            tail = nextHead?.next
        }
        return dummyHead.next
    }

    private fun reverseNode(pre: ListNode?, tail: ListNode?) {
        var preNode: ListNode? = tail?.next
        var curNode = pre?.next
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