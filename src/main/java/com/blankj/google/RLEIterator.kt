package com.blankj.google

class RLEIterator(encoding: IntArray) {

    val stack = ArrayDeque<Int>()

    init {
        for (i in encoding.indices.reversed()) {
            stack.add(encoding[i])
        }
    }

    fun next(n: Int): Int {
        var cur = n
        var lastElement = -1
        while (stack.isNotEmpty() && cur > stack.last()) {
            cur -= stack.removeLast()
            lastElement = stack.removeLast()
        }
        if (stack.isEmpty() && cur == 0) {
            return lastElement
        }
        if (stack.isEmpty()) {
            return -1
        }
        var count = stack.removeLast()
        lastElement = stack.last()
        count -= cur
        stack.add(count)
        return lastElement
    }

}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * var obj = RLEIterator(encoding)
 * var param_1 = obj.next(n)
 */