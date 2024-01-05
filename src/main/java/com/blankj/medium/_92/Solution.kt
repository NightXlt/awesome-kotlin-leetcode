package com.blankj.medium._92

import com.blankj.structure.ListNode


class Solution {
    // 通过 dummy 将 head 的操作差异抹平了
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        val dummyNode = ListNode(-1)
        dummyNode.next = head
        var pre = dummyNode
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        repeat(left - 1) { pre = pre.next!! }
        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        var succ  = pre
        repeat(right - left + 1) { succ = succ.next!! }

        // 第 3 步：切断出一个子链表（截取链表）
        val leftNode = pre.next
        var curr: ListNode? = succ.next
        pre.next = null
        succ.next = null
        // 第 4 步：反转链表的子区间
        reversNode(leftNode!!)

        pre.next = succ
        leftNode.next = curr
        return dummyNode.next
    }

    private fun reversNode(head: ListNode) {
        var curr: ListNode? = head
        //反转
        var preNode: ListNode? = null
        while (curr != null) {
            val tmp = curr.next
            curr.next = preNode
            preNode = curr
            curr = tmp
        }
    }
}

fun main() {
    Solution().reverseBetween(
        ListNode.createTestData("[1,2,3,4,5]"),
        2, 4

    )
}