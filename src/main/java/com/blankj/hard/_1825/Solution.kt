package com.blankj.hard._1825

import com.blankj.ext.print
import java.util.*
import kotlin.collections.ArrayDeque

class MKAverage(private val m: Int, private val k: Int) {

    private var sum = 0L

    private val queue = ArrayDeque<Int>()

    private val countMap = TreeMap<Int, Int>()

    fun addElement(num: Int) {
        countMap.merge(num, 1, Integer::sum)
        queue.add(num)
        sum += num
        if (queue.size <= m) return
        val lruElement = queue.removeFirst()
        sum -= lruElement
        countMap.merge(lruElement, -1, Integer::sum)
        if (countMap[lruElement] == 0) {
            countMap.remove(lruElement)
        }
    }

    fun calculateMKAverage(): Int {
        if (queue.size < m) return -1
        return ((sum - getSumMaxK() - getSumMinK()) / (m - 2 * k)).toInt()
    }


    private fun getSumMaxK(): Long {
        var kSum = 0L
        var max = Int.MAX_VALUE
        var i = k
        while (i > 0) {
            val (value, count) = countMap.floorEntry(max)
            if (count > i) {
                kSum += i * value
                return kSum
            }
            kSum += count * value
            i -= count
            max = value - 1
        }
        return kSum
    }

    private fun getSumMinK(): Long {
        var kSum = 0L
        var min = Int.MIN_VALUE
        var i = k
        while (i > 0) {
            val (value, count) = countMap.ceilingEntry(min)
            if (count > i) {
                kSum += i * value
                return kSum
            }
            kSum += count * value
            i -= count
            min = value + 1
        }
        return kSum
    }
}


fun main() {
    val mkAverage = MKAverage(3, 1)
    mkAverage.addElement(3)
    mkAverage.addElement(1)
    mkAverage.calculateMKAverage().print()
    mkAverage.addElement(10)
    mkAverage.calculateMKAverage().print()

}