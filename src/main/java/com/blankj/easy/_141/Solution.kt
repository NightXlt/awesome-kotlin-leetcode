package com.blankj.easy._141

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun hasCycle(head: ListNode?): Boolean {
        var fastNode: ListNode? = head ?: return false
        var slowNode: ListNode?  = head
        while (fastNode?.next != null) {
            fastNode = fastNode.next.next
            slowNode = slowNode?.next
            if (fastNode == slowNode) {
                return true
            }
        }
        return false
    }
}