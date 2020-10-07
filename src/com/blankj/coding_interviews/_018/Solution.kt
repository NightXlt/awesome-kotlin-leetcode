package com.blankj.coding_interviews._018

import com.blankj.coding_interviews._004.print
import com.blankj.structure.ListNode

class Solution {

    fun deleteNode(head: ListNode?, `val`: Int): ListNode? {
        head ?: return head
        if (head.`val` == `val`) {
            return head.next
        }
        head.next = deleteNode(head.next, `val`)
        return head
    }

    // coding interview version (In the event that deleted node is in list)
    fun deleteNode2(head: ListNode?, deletedNode: ListNode): ListNode? {
        head ?: return head
        if (deletedNode.next != null) { // not last node
            deletedNode.`val` = deletedNode.next.`val`
            deletedNode.next = deletedNode.next.next
            return head
        }
        // just one node
        if (head.`val` == deletedNode.`val`) return null

        // traverse nodes
        var cur = head
        while (cur?.next != null && cur.next != deletedNode) {
            cur = cur.next
        }
        if (cur?.next != null) {
            cur.next = cur.next?.next
            return head
        }
        throw IllegalArgumentException("deleted node isn't in list")
    }
}


fun main() {
    Solution().deleteNode(null, 5)?.print() // null
    Solution().deleteNode(ListNode.createTestData("[]"), 5)?.print() // empty list
    Solution().deleteNode(ListNode.createTestData("[4,5,1,9]"), 5)?.print() // has next node
    Solution().deleteNode(ListNode.createTestData("[5]"), 5)?.print() // one node
    Solution().deleteNode(ListNode.createTestData("[4,5,1,9]"), 9)?.print() // last node and length is more than 1
    Solution().deleteNode(ListNode.createTestData("[4,5,1,9]"), 10)?.print() // illegal status
}
