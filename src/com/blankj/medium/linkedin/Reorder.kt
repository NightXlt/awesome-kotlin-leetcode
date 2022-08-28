package com.blankj.medium.linkedin

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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
            // break up last node as curTailNode
            val tailPrev = curTailNode?.prev
            tailPrev?.next = null
            // insert curTailNode behind current node
            val nextNode = curNode.next
            curNode.next = curTailNode
            curTailNode?.prev = curNode
            curTailNode?.next = nextNode
            nextNode?.prev = curTailNode
            // update cur node and tail node
            curNode = nextNode
            curTailNode = tailPrev
        }
        // update tail node
        tail = curTailNode
    }
}
public inline fun <T> T.also(block: (T) -> Unit) : T {
    block(this)
    return this
}

public inline fun <T, R> T.run(block: (T) -> R) : R {
    return block(this)
}
fun main() {
    /** test cases
     * - [1, 2, 3, 4, 5]
     * - [1, 2, 3, 4, 5, 6]
     * - []
     * - [1]
     */
}