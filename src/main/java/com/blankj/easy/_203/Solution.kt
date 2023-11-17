package com.blankj.easy._203

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        head ?: return null
        val dummy = ListNode(0)
        dummy.next = head
        var node: ListNode? = dummy
        while (node?.next != null) {
            while (node.next != null && node.next.`val` == `val`) {
                node.next = node.next!!.next
            }
            node = node.next
        }
        return dummy.next
    }
}

fun main() {
    Solution().removeElements(
        ListNode.createTestData("[1,2,6,3,4,5,6]"),
        6
    )?.print()
    Solution().removeElements(
        ListNode.createTestData("[]"),
        6
    )?.print()
    Solution().removeElements(
        ListNode.createTestData("[7,7,7,7]"),
        7
    )?.print()
}