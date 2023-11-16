package com.blankj.medium._02_05

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null || l2 == null) return null
        var first = l1
        var second = l2
        var canary = 0
        var dummy = ListNode(0)
        var cur = dummy
        while (first != null || second != null) {
            val sum = (first?.`val` ?: 0) + (second?.`val` ?: 0) + canary
            val node = ListNode(sum % 10)
            canary = sum / 10
            cur.next = node
            cur = node
            first = first?.next
            second = second?.next
        }

        if (canary != 0) {
            cur.next = ListNode(1)
        }
        return dummy.next
    }

//    private fun reverseList(head: ListNode?): ListNode? {
//        var cur = head
//        var pre: ListNode? = null
//        while (cur != null) {
//            val next = cur.next
//            cur.next = pre
//            pre = cur
//            cur = next
//        }
//        return pre
//    }
}

fun main() {
    Solution().addTwoNumbers(
        ListNode.createTestData("[7,1,6]"),
        ListNode.createTestData("[5,9,2]"),
    )?.print()
    Solution().addTwoNumbers(
        ListNode.createTestData("[6,1,7]"),
        ListNode.createTestData("[2,9,5]"),
    )?.print()
}