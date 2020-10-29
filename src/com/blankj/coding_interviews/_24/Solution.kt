package com.blankj.coding_interviews._24

import com.blankj.coding_interviews._004.print
import com.blankj.structure.ListNode


class Solution {

    fun reverseList(head: ListNode?): ListNode? {
        var reverseHeadNode: ListNode? = null
        var preNode: ListNode? = null
        var nextNode: ListNode? = null
        var curNode = head
        while (curNode != null) {
            nextNode = curNode.next
            if (nextNode == null) {
                reverseHeadNode = curNode
            }
            curNode.next = preNode
            preNode = curNode
            curNode = nextNode
        }
        return reverseHeadNode
    }

    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        l1 ?: return l2
        l2 ?: return l1
        var mergeNode: ListNode?
        if (l1.`val` <= l2.`val`) {
            mergeNode = l1
            mergeNode.next = mergeTwoLists(l1.next, l2)
        } else {
            mergeNode = l2
            mergeNode.next = mergeTwoLists(l1, l2.next)
        }
        return mergeNode
    }
}

fun main() {
    Solution().reverseList(null)?.print()
    Solution().reverseList(ListNode(1))?.print()
    Solution().reverseList(ListNode.createTestData("[1,2,3,4]"))?.print()
}