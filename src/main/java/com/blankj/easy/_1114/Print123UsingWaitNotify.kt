package com.blankj.easy._1114

import kotlin.concurrent.thread

@Volatile
private var state = 0
private val LOCK = Object()
fun main() {
    thread {
        print("1", 0)
    }
    thread {
        print("2", 1)
    }
    thread {
        print("3", 2)
    }

}

private fun print(content: String, target: Int) {
    while (true) {
        synchronized(LOCK) {
            while (state % 3 != target) {
                LOCK.wait()
            }
            println(content)
            state++
            LOCK.notifyAll()
        }
    }
}