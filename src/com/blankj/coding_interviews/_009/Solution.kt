package com.blankj.coding_interviews._009

import com.blankj.coding_interviews._004.print
import java.util.*

class Solution

class CQueue {

    var sPush = ArrayDeque<Int>()
    var sPop = ArrayDeque<Int>()

    fun appendTail(value: Int) {
        sPush.push(value)
    }

    fun deleteHead(): Int {
        if (sPush.isEmpty() && sPop.isEmpty()) return -1 // 这里的异常处理要和面试官沟通
        if (sPop.isNotEmpty()) {
            return sPop.pop()
        }
        while (sPush.size > 1) {
            sPop.push(sPush.pop())
        }
        return sPush.pop()
    }

}

class CStack {

    var queue = ArrayDeque<Int>()

    /** Push element x onto stack. */
    fun push(x: Int) {
        queue.offer(x)
        val size = queue.size // Note: do not use queue.size > 1 condition to traverse directly
        repeat(size - 1) {
            queue.offer(queue.poll())
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        if (queue.isEmpty()) return -1
        return queue.poll()
    }

    /** Get the top element. */
    fun top(): Int {
        if (queue.isEmpty()) return -1
        return queue.peek()
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return queue.isEmpty()
    }
}

fun main() {
//    CQueue().deleteHead().print() // illegal operation
    CQueue().apply { // normal case
        appendTail(1)
        appendTail(2)
        appendTail(3)
        deleteHead().print()
        deleteHead().print()
        deleteHead().print()
    }
    CQueue().apply { // out more than in
        appendTail(1)
        deleteHead().print()
        deleteHead().print()
    }
}