package com.blankj.coding_interviews._006

import com.blankj.ext.print
import com.blankj.structure.ListNode

class Solution {
    fun reversePrint(head: ListNode?): IntArray {
        var p: ListNode? = head ?: return intArrayOf()
        val stack = ArrayDeque<Int>()
        while (p != null) {
            stack.add(p.`val`)
            p = p.next
        }
        return stack.toIntArray()
    }

    fun reversePrint1(head: ListNode?) {
        val p: ListNode = head ?: return
        reversePrint1(p.next)
        print("${p.`val`} ")
    }

    fun reversePrint2(head: ListNode?): IntArray {
        var p: ListNode? = head
        var len = 0
        while (p != null) {
            p = p.next
            len++
        }
        val result = IntArray(len)
        p = head
        while (p != null) {
            result[--len] = p.`val`
            p = p.next
        }
        return result
    }
}

fun main() {
    // test cases
    val nullNode = null // nullable node
    val oneNode = ListNode.createTestData("[1]") // one node
    val twoNodes = ListNode.createTestData("[1,2]") // multi nodes
    val fiveNodes = ListNode.createTestData("[1,2,3,4,5]")
    Solution().reversePrint2(nullNode).print()
    Solution().reversePrint2(oneNode).print()
    Solution().reversePrint2(twoNodes).print()
    Solution().reversePrint2(fiveNodes).print()
}