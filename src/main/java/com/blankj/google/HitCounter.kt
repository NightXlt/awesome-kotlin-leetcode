package com.blankj.google

class HitCounter {

    private var count = 0
    private var queue = ArrayDeque<IntArray>()

    fun hit(timestamp: Int) {
        if (queue.isNotEmpty() && queue.last()[1] == timestamp) {
            queue.last()[1]++
        } else {
            queue.add(intArrayOf(timestamp, 1))
        }
        count++
    }

    fun getHits(timestamp: Int): Int {
        while (queue.isNotEmpty() && queue.first()[0] <= timestamp - RANGE) {
            count -= queue.removeFirst()[1]
        }
        return count
    }

    companion object {
        const val RANGE = 300
    }
}

fun main() {
    val hitCounter = HitCounter()
    hitCounter.hit(1)
    hitCounter.hit(2)
    hitCounter.hit(3)
    println(hitCounter.getHits(4))
    hitCounter.hit(300)
    println(hitCounter.getHits(300))
    println(hitCounter.getHits(301))

}