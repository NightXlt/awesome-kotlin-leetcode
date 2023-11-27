package com.blankj.easy._1114

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

suspend fun main(args: Array<String>) = runBlocking {
    // 定义一个信号量，初始值为 1
    val mutexA = Mutex(false)
    val mutexB = Mutex(true)
    val mutexC = Mutex(true)

    // 启动三个协程
    val job1 = GlobalScope.launch {
        print("1", mutexA, mutexB)
    }
    val job2 = GlobalScope.launch {
        print("2", mutexB, mutexC)
    }
    val job3 = GlobalScope.launch {
        print("3", mutexC, mutexA)

    }
    delay(30000L)
}

private suspend fun print(content: String, current: Mutex, next: Mutex) {
    while (true) {
        current.lock()
        println(content)
        next.unlock()
    }
}