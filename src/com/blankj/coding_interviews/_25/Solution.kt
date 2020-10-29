package com.blankj.coding_interviews._25

import com.blankj.coding_interviews._004.print
import com.blankj.structure.ListNode
import sun.misc.Cleaner


class Solution {

//    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
//        l1 ?: return l2
//        l2 ?: return l1
//        val mergeNode: ListNode?
//        if (l1.`val` <= l2.`val`) {
//            mergeNode = l1
//            mergeNode.next = mergeTwoLists(l1.next, l2)
//        } else {
//            mergeNode = l2
//            mergeNode.next = mergeTwoLists(l1, l2.next)
//        }
//        return mergeNode
//    }

    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1: ListNode? = l1 ?: return l2
        var l2: ListNode? = l2 ?: return l1
        val dummyHead: ListNode? = ListNode(-1)
        var p = dummyHead
        while (l1 != null && l2 != null) {
            if (l1.`val` > l2.`val`) {
                p?.next = l2
                l2 = l2.next
            } else {
                p?.next = l1
                l1 = l1.next
            }
            p = p?.next
        }
        p?.next = l1 ?: l2
        return dummyHead?.next
    }
}

fun main() {
    Solution().mergeTwoLists(null, ListNode(1))?.print()
    Solution().mergeTwoLists(ListNode(1), null)?.print()
    Solution().mergeTwoLists(ListNode(1), ListNode.createTestData("[2,3,4]"))?.print()
}