package com.blankj.medium._445

import com.blankj.coding_interviews._004.print
import com.blankj.structure.ListNode
import java.util.*

class Solution {

    /**
     * 题眼：需要从低位相加，但头指针指向的是高位，要么反转链表，要么借助辅助空间帮助实现逆序，比如栈
     * 如果不允许反转链表，那么通过栈完成逆序的操作,栈里只需要存数字就行了哈，结果的链表节点反正需要新建
     * 如果允许且需要空间复杂度为1的话，那么就可以反转链表。将输入链表逆序后，就是链表相加那道题了
     * 最后还需要把结果链表反一下即为所求
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        l1 ?: return l2
        l2 ?: return l1
        val s1 = ArrayDeque<Int>()
        val s2 = ArrayDeque<Int>()
        addListNodeToStack(l1, s1)
        addListNodeToStack(l2, s2)
        var result: ListNode? = null
        var carry = 0
        while (s1.isNotEmpty() || s2.isNotEmpty() || carry != 0) {
            val a = s1.removeLastOrDefault(0)
            val b = s2.removeLastOrDefault(0)
            val sum = a + b + carry
            val curNode = ListNode(sum % 10)
            carry = sum / 10
            curNode.next = result
            result = curNode
        }
        return result
    }

    private fun <E> ArrayDeque<E>.removeLastOrDefault(default: E): E {
        if (isEmpty()) return default
        return removeLast()
    }

    private fun addListNodeToStack(l1: ListNode?, s1: ArrayDeque<Int>) {
        var p1: ListNode? = l1
        while (p1 != null) {
            s1.add(p1.`val`)
            p1 = p1.next
        }
    }
}

fun main() {
    Solution().addTwoNumbers(ListNode.testCase0(), ListNode.testCase0())?.print()
    Solution().addTwoNumbers(ListNode.testCase1(), ListNode.testCase2())?.print()
    Solution().addTwoNumbers(ListNode.testCase1(), ListNode.testCase0())?.print()
}
