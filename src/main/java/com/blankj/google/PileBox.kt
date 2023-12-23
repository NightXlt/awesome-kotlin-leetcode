package com.blankj.google

import com.blankj.ext.print
import java.util.TreeMap

class PileBox {
    fun sol(boxs: List<Pair<Int, Int>>): Int {
        val allBoxs = mutableListOf<Triple<Int, Int, Int>>()
        val seen = mutableSetOf<Int>()

        for ((index, box) in boxs.withIndex()) {
            allBoxs.add(Triple(box.first, box.second, index))
            allBoxs.add(Triple(box.second, box.first, index))
        }

        allBoxs.sortWith(compareBy({ it.first }, { it.second }))

        var curMax = Int.MIN_VALUE
        for (box in allBoxs) {
            if (box.third in seen || box.first <= curMax) {
                continue
            }
            seen.add(box.third)
            curMax = box.first
        }

        return seen.size
    }

}

fun main() {
    PileBox().sol(
        listOf(
            2 to 100,
            2 to 2
        )
    ).print()
}