package com.blankj.medium.template

class Solution {


}

fun main() {
    listOf(0, 1)
    var sum = "sdf"
    val runnable = Thread(Runnable { sum = "123" })
    runnable.start()
    runnable.join()
    print(sum)
}