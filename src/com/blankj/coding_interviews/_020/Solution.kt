package com.blankj.coding_interviews._020

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {

    fun getKthFromEnd(head: ListNode?, k: Int): ListNode? {
        if (k <= 0) return null
        var fastNode: ListNode? = head ?: return head
        var slowNode = head
        repeat(k) {
            // size is less than K
            if (fastNode == null) return null
            fastNode = fastNode?.next
        }
        while (fastNode != null) {
            fastNode = fastNode?.next
            slowNode = slowNode?.next
        }
        return slowNode
    }
}


fun main() {
    Solution().getKthFromEnd(null, 2)?.print() // nullable node
    Solution().getKthFromEnd(ListNode(1), 0)?.print() // illegal k
    Solution().getKthFromEnd(ListNode(1), 2)?.print() // k is more than nodes' length
    Solution().getKthFromEnd(ListNode(1), 1)?.print() // one node
    Solution().getKthFromEnd(ListNode.createTestData("[1,3,5,7,9]"), 2)?.print() // normal case
}
