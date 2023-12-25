package com.blankj.google

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

class CanMeasureWater {
    fun canMeasureWater(jug1Capacity: Int, jug2Capacity: Int, targetCapacity: Int): Boolean {
        val stack = ArrayDeque<IntArray>()
        val visited = mutableSetOf<Long>()
        stack.add(intArrayOf(0, 0))
        while (stack.isNotEmpty()) {
            if (hash(stack.last()) in visited) {
                stack.removeLast()
                continue
            }
            val cur = stack.removeLast()
            visited.add(hash(cur))
            val (remainX, remainY) = cur
            if (remainX == targetCapacity || remainY == targetCapacity || (remainX + remainY) == targetCapacity) {
                return true
            }
            stack.add(intArrayOf(jug1Capacity, remainY))
            stack.add(intArrayOf(remainX, jug2Capacity))
            stack.add(intArrayOf(0, remainY))
            stack.add(intArrayOf(remainX, 0))
            val pourIntoY = min(remainX, jug2Capacity - remainY)
            stack.add(intArrayOf(remainX - pourIntoY, remainY + pourIntoY))
            val pourIntoX = min(remainY, jug1Capacity - remainX)
            stack.add(intArrayOf(remainX + pourIntoX, remainY - pourIntoX))

        }
        return false
    }

    private fun hash(state: IntArray): Long {
        return 1000001 * state[0].toLong() + state[1]
    }

}
