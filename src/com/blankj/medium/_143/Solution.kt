package com.blankj.medium._143

import com.blankj.coding_interviews._004.print
import com.blankj.structure.ListNode

class Solution {
    fun reorderList(head: ListNode?): Unit {
        head ?: return
        val mid: ListNode = findMiddleNode(head)
        val l2 = reverseOrder(mid.next)
        mid.next = null
        mergeList(head, l2)
    }

    private fun mergeList(head: ListNode?, mid: ListNode?) {
        var l1 = head
        var l2 = mid
        var nextTemp1: ListNode?
        var nextTemp2: ListNode?
        while (l1 != null && l2 != null) {
            nextTemp1 = l1.next
            nextTemp2 = l2.next
            l1.next = l2
            l1 = nextTemp1

            // construct swapped node's next pointer
            // 1-> 2 -> 3 -> 4
            // 1-> 2 -> 4 -> 3
            // 1-> 4  2 -> 3
            //     |       ^
            //     |       |
            //     —————————
            l2.next = l1
            l2 = nextTemp2
        }
    }

    private fun reverseOrder(node: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = node
        while (cur != null) {
            val next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        }
        return pre
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
    Solution().reorderList(null).print()
    Solution().reorderList(ListNode.testCase3()).print()
    Solution().reorderList(ListNode.testCase1()).print()
    Solution().reorderList(ListNode.palindromeLinkedList()).print()
}
