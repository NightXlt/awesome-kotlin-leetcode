package com.blankj.medium._019

import com.blankj.structure.ListNode

class RemoveNthFromEnd {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        head ?: return null
        val dummy = ListNode(0)
        dummy.next = head
        var fastNode: ListNode? = head
        var slowNode: ListNode? = dummy
        repeat(n) { fastNode = fastNode?.next }
        while (fastNode != null) {
            fastNode = fastNode?.next
            slowNode = slowNode?.next
        }
        slowNode?.next = slowNode?.next?.next
        return dummy.next
    }
}