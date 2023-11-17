package com.blankj.medium._002

import com.blankj.ext.print
import com.blankj.structure.ListNode

class SolutionKT1 {

    fun addTwoNums(l1: ListNode?, l2: ListNode?): ListNode? {
        var sum = 0
        val result = ListNode(0)
        var l1 = l1
        var l2 = l2
        var p: ListNode = result
        while (l1 != null || l2 != null) {
            sum /= 10
            sum += l1?.`val` ?: 0
            sum += l2?.`val` ?: 0
            p.next = ListNode(sum % 10)
            p = p.next
            l1 = l1?.next
            l2 = l2?.next
        }
        if (sum >= 10) {
            p.next = ListNode(1)
        }
        return result.next
    }
}

fun main() {
    SolutionKT1().addTwoNums(ListNode.createTestData("[9,9,9,9,9,9,9]"),
            ListNode.createTestData("[9,9,9,9]"))?.print()
}