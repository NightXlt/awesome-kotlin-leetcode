package com.blankj.hard._295

import com.blankj.ext.print
import java.util.*

// Insert sort
class MedianFinder() {

    /** initialize your data structure here. */

    private val data = mutableListOf<Int>()

    fun addNum(num: Int) {
        if (data.isEmpty()) {
            data.add(num)
            return
        }
        data.add(Int.MIN_VALUE)
        var i = data.lastIndex
        while (i > 0) {
            if (num < data[i - 1]) {
                data[i] = data[i - 1]
            } else {
                break
            }
            i--
        }
        data[i] = num
    }

    fun findMedian(): Double {
        val size = data.size
        val m = size / 2
        return if (size % 2 == 0) (data[m] + data[m - 1]) / 2.0 else data[m].toDouble()
    }

}

class MedianFinderHeap() {

    /** initialize your data structure here. */

    private val maxHeap = PriorityQueue<Int>() { a, b -> b - a }
    private val minHeap = PriorityQueue<Int>()

    fun addNum(num: Int) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num)
            return
        }
        if (num <= maxHeap.peek()) {
            maxHeap.add(num)
        } else {
            minHeap.add(num)
        }
        if (maxHeap.size > minHeap.size + 1) {
            minHeap.add(maxHeap.remove())
        }
        if (minHeap.size > maxHeap.size) {
            maxHeap.add(minHeap.remove())
        }
    }

    fun findMedian(): Double {
        if (maxHeap.size > minHeap.size) {
            return maxHeap.peek().toDouble()
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0
    }

}


fun main() {
    MedianFinderHeap().apply {
        addNum(1)
        addNum(2)
        findMedian().print()
        addNum(3)
        findMedian().print()
    }
}