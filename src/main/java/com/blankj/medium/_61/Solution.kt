package com.blankj.medium._61

import com.blankj.ext.print
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
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null) return head
        var length = 1
        var p = head
        while (p?.next != null) {
            length++
            p = p.next
        }
        p?.next = head
        var k = k % length
        p = head
        repeat(length - k - 1) { p = p?.next }
        val newHead = p?.next
        p?.next = null
        return newHead
    }
}

fun main() {
    Solution().rotateRight(ListNode.createTestData("[1,2,3,4,5]"), 2)?.print()
}