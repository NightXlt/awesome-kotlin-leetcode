package com.blankj.easy._1114

import java.util.concurrent.Semaphore
import kotlin.concurrent.thread


fun main() {
    val semaphoreA = Semaphore(1) // 只有A 初始信号量为1,第一次获取到的只能是A

    val semaphoreB = Semaphore(0)
    val semaphoreC = Semaphore(0)
    thread {
        print("1", semaphoreA, semaphoreB)
    }
    thread {
        print("2", semaphoreB, semaphoreC)
    }
    thread {
        print("3", semaphoreC, semaphoreA)
    }
}

private fun print(content: String, current: Semaphore, next: Semaphore) {
    while (true) {
        current.acquire()
        println(content)
        next.release()
    }
}