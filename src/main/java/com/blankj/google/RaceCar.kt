package com.blankj.google

import com.blankj.ext.print

class RaceCar {
    fun racecar(target: Int): Int {
        if (target <= 0) return 0
        val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        queue.add(0 to 1)
        val visited = mutableSetOf("0#1")
        var res = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val (position, speed) = queue.removeFirst()
                if (position == target) return res
                var newPosition = position + speed
                var newSpeed = speed * 2
                var flag = "$newPosition#$newSpeed"
                if (flag !in visited && newPosition > 0 && newPosition < target * 2) {
                    visited.add(flag)
                    queue.add(newPosition to newSpeed)
                }
                newPosition = position
                newSpeed = if (speed > 0) -1 else 1
                flag = "$newPosition#$newSpeed"
                if (flag !in visited && newPosition > 0 && newPosition < target * 2) {
                    visited.add(flag)
                    queue.add(newPosition to newSpeed)
                }
            }
            res++
        }
        return -1
    }
}

fun main() {
    RaceCar().racecar(3).print()
    RaceCar().racecar(6).print()
}