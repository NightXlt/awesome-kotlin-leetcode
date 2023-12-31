package com.blankj.medium._86
import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun partition(head: ListNode?, x: Int): ListNode? {
        val dummySmall = ListNode(0)
        var small = dummySmall
        val dummyBig = ListNode(0)
        var big = dummyBig
        var cur = head
        while (cur != null) {
            if (cur.`val` < x) {
                small.next = ListNode(cur.`val`)
                small = small.next
            } else {
                big.next = ListNode(cur.`val`)
                big = big.next
            }
            cur = cur.next
        }
        small.next = dummyBig.next
        return dummySmall.next
    }
}

fun main() {

}