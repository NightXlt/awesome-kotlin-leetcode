package com.blankj.easy._234

import com.blankj.coding_interviews._004.print
import com.blankj.structure.ListNode

class Solution {
    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null) return true
        val midNode = findMiddleNode(head)
        val secondHalfStart = reverseOrder(midNode.next)
        var result = true
        var p1 = head
        var p2 = secondHalfStart
        while (p2 != null) {
            if (p1?.`val` != p2.`val`) {
                result = false
                break
            }
            p1 = p1.next
            p2 = p2.next
        }
        midNode.next = reverseOrder(secondHalfStart)
        return result
    }

    private fun reverseOrder(node: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur = node
        while (cur != null) {
            val next = cur.next
            cur.next = pre
            pre = cur
            cur = next
        }
        return pre
    }

    private fun findMiddleNode(head: ListNode): ListNode {
        var fast = head
        var slow = head
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next
            slow = slow.next
        }
        return slow
    }
}

fun main() {
    Solution().isPalindrome(null).print()
    Solution().isPalindrome(ListNode.testCase0()).print()
    Solution().isPalindrome(ListNode.testCase1()).print()
    Solution().isPalindrome(ListNode.palindromeLinkedList()).print()
}
