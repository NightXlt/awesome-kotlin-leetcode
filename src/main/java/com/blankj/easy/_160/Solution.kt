package com.blankj.easy._160

import com.blankj.structure.ListNode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution {
    fun getIntersectionNode(head1: ListNode?, head2: ListNode?): ListNode? {
        if (head1 == null || head2 == null) return null
        val len1 = getLength(head1)
        val len2 = getLength(head2)
        var d = len1 - len2
        var longNodes: ListNode? = head1
        var shortNodes: ListNode? = head2
        if (d < 0) {
            longNodes = head2
            shortNodes = head1
            d *= -1
        }
        for (i in 1..d) {
            longNodes = longNodes?.next ?: return null
        }
        while (longNodes != null) {
            if (longNodes == shortNodes) {
                return shortNodes
            }
            longNodes = longNodes.next
            shortNodes = shortNodes?.next
        }
        return null
    }

    private fun getLength(head: ListNode?): Int {
        var cur: ListNode? = head ?: return 0
        var length = 0
        while (cur != null) {
            length++
            cur = cur.next
        }
        return length
    }
}