package com.blankj.coding_interviews._30

import com.blankj.ext.print
import java.util.*
import kotlin.NoSuchElementException


class MinStack {

    /** initialize your data structure here. */
    private val stack = ArrayDeque<Int>()
    private val minStack = ArrayDeque<Int>()

    fun push(x: Int) {
        stack.push(x)
        if (minStack.isEmpty() || minStack.peek() > x) {
            minStack.push(x)
        } else {
            minStack.push(minStack.peek())
        }
    }

    fun pop() {
        if (stack.isEmpty()) return
        stack.pop()
        minStack.pop()
    }

    fun top(): Int {
        return stack.peek() ?: throw NoSuchElementException("stack is empty")
    }

    fun min(): Int {
        return minStack.peek() ?: throw NoSuchElementException("stack is empty")
    }
}

fun main() {
    val minStack = MinStack()
//    minStack.push(-2);
//    minStack.push(0);
//    minStack.push(-3);
//    minStack.min().print();
//    minStack.pop().print()
//    minStack.top().print()
//    minStack.min().print()
    minStack.min().print()
}
