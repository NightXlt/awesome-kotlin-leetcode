package com.blankj.hard._023

import com.blankj.structure.ListNode
import kotlinx.coroutines.flow.merge

class Solution {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null
        return mergeSort(lists, 0, lists.lastIndex)
    }

    private fun mergeSort(lists: Array<ListNode?>, left: Int, right: Int): ListNode? {
        if (left < right) {
            val mid = (left + right) shr 1
            val leftListRoot = mergeSort(lists, left, mid)
            val rightListRoot = mergeSort(lists, mid + 1, right)
            return mergeTwoLists(leftListRoot, rightListRoot)
        }
        return lists[left]
    }

    private fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) {
            return l2
        } else if (l2 == null) {
            return l1
        }
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