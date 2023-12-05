package com.blankj.medium._024

import com.blankj.structure.ListNode

class SwapPairs {
    fun swapPairs(head: ListNode?): ListNode? {
        head ?: return null
        if (head.next == null) return head
        val dummy = ListNode(0)
        dummy.next = head
        var cur = dummy
        while (cur.next != null && cur.next.next != null) {
            val next1 = cur.next
            val next2 = cur.next.next
            next1.next = next2.next
            next2.next = next1
            cur.next = next2
            cur = next1
        }
        return dummy.next
    }
}