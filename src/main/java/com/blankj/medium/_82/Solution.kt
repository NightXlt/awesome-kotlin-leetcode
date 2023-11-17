package com.blankj.medium._82

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        head ?: return null
        val dummyHead = ListNode(0).apply {
            next = head
        }
        var p = dummyHead
        while (p.next != null && p.next.next != null) {
            if (p.next.`val` == p.next?.next?.`val`) {
                val x = p.next.`val`
                while (p.next != null && p.next.`val` == x) {
                    p.next = p.next?.next
                }
            } else {
                p = p.next
            }
        }
        return dummyHead.next
    }
}

fun main() {
    Solution().deleteDuplicates(null)?.print()
    Solution().deleteDuplicates(ListNode.testCase0())?.print()
    Solution().deleteDuplicates(ListNode.testCase1())?.print()
    Solution().deleteDuplicates(ListNode.testCase2())?.print()
    Solution().deleteDuplicates(ListNode.testCase3())?.print()
}
