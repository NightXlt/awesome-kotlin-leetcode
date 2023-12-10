package com.blankj.medium._142

import com.blankj.structure.ListNode

class Solution {
    fun detectCycle(head: ListNode?): ListNode? {
        head ?: return null
        var fastNode: ListNode? = head
        var slowNode: ListNode? = head
        while (fastNode?.next != null) {
            fastNode = fastNode.next?.next
            slowNode = slowNode?.next
            if (fastNode == slowNode) {
                break
            }
        }
        // fastNode.next != null: one node case
        if (fastNode == slowNode && fastNode?.next != null) {
            slowNode = head
            while (slowNode != fastNode) {
                slowNode = slowNode?.next
                fastNode = fastNode?.next
            }
            return fastNode
        }
        // no cycle node
        return null
    }
}