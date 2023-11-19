package com.blankj.easy._346

import com.blankj.ext.print


class MovingAverage(val size: Int) {

    val queue = ArrayDeque<Int>()

    var sum = 0

    fun next(`val`: Int): Double {
        queue.add(`val`)
        sum += `val`
        if (queue.size > size) {
            sum -= queue.removeFirst()
        }
        return sum / queue.size.toDouble()
    }

}

fun main() {
    val movingAverage = MovingAverage(3)
    movingAverage.next(1).print() // return 1.0 = 1 / 1

    movingAverage.next(10).print() // return 5.5 = (1 + 10) / 2

    movingAverage.next(3).print() // return 4.66667 = (1 + 10 + 3) / 3

    movingAverage.next(5).print() // return 6.0 = (10 + 3 + 5) / 3

}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * var obj = MovingAverage(size)
 * var param_1 = obj.next(`val`)
 */