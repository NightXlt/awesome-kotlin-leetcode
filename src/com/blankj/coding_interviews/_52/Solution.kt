package com.blankj.coding_interviews._52

import com.blankj.ext.print
import com.blankj.structure.ListNode


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class Solution {

    fun getIntersectionNode(head1: ListNode?, head2: ListNode?): ListNode? {
        if (head1 == null || head2 == null) return null
        val len1 = getLength(head1)
        val len2 = getLength(head2) // 这里 getLength 传的参数不要传错
        var d = len1 - len2
        var longNodes: ListNode? = head1
        var shortNodes: ListNode? = head2
        if (d < 0) {
            longNodes = head2
            shortNodes = head1
            d *= -1
        }
        for (i in 1..d) {
            longNodes = longNodes?.next ?: break
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

fun main() {

    val crossNode = ListNode(8).apply {
        next = ListNode(4).apply {
            next = ListNode(5)
        }
    }
    val head1 = ListNode(4)
            .apply {
                next = ListNode(1).apply {
                    next = crossNode
                }
            }
    val head2 = ListNode(5)
            .apply {
                next = ListNode(6).apply {
                    next = ListNode(1).apply {
                        next = crossNode
                    }
                }
            }
    Solution().getIntersectionNode(head1, head2)

}
