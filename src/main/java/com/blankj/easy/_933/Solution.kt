package com.blankj.easy._933

import com.blankj.ext.print

class RecentCounter {

    val queue = ArrayDeque<Int>()

    fun ping(t: Int): Int {
        queue.add(t)
        while (queue.first() + WINDOW_SIZE < t) {
            queue.removeFirst()
        }
        return queue.size
    }

    companion object {
        const val WINDOW_SIZE = 3000
    }
}

fun main() {
    val recentCounter = RecentCounter()
    recentCounter.ping(1).print()
    recentCounter.ping(100).print()
    recentCounter.ping(3001).print()
    recentCounter.ping(3002).print()
}