package com.blankj.google
// 359
class Logger() {
    val recorder = mutableMapOf<String, Int>()

    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
        if (message !in recorder) {
            recorder[message] = timestamp
            return true
        }
        val previousTimeStamp = recorder.getValue(message)
        if (timestamp >= previousTimeStamp + 10) {
            recorder[message] = timestamp
            return true
        }
        return false
    }
}