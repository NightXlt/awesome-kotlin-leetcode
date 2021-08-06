package com.blankj.medium.linkedin

class ListNode(
    val value: Int
) {
    var next: ListNode? = null
    var prev: ListNode? = null
}

class DoubleLinkedList {

    var head: ListNode? = null

    var tail: ListNode? = null

    fun reorder() {
        if (head == null || tail == null) return
        var curNode = head
        var curTailNode = tail
        while (curNode?.next?.next != null) {
            val tailPrev = curTailNode?.prev
            tailPrev?.next = null
            val nextNode = curNode.next
            curNode.next = curTailNode
            curTailNode?.prev = curNode
            curTailNode?.next = nextNode
            nextNode?.prev = curTailNode
            curNode = nextNode
            curTailNode = tailPrev
        }
        tail = curTailNode
    }
}

fun main() {

}