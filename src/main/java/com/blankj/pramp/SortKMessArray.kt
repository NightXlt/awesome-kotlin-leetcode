package com.blankj.pramp

import com.blankj.ext.print
import java.util.*
import kotlin.math.min

class SortKMessArray {
    fun sortKMessedArray(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        for (i in 0 until n) {
            var minIndex = i
            for (j in i + 1 until min(i + k + 1, n)) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }
            if (minIndex != i) {
                val temp = arr[minIndex]
                arr[minIndex] = arr[i]
                arr[i] = temp
            }
        }
        return arr
    }

    fun sortKMessedArrayWithHeap(arr: IntArray, k: Int): IntArray {
        if (arr.isEmpty()) return arr
        val heap = PriorityQueue<Int>()
        val minCount = k + 1
        for (i in 0..<min(minCount, arr.size)) {
            heap.add(arr[i])
        }
        var index = 0
        for (i in minCount..<arr.size) {
            arr[index++] = heap.remove()
            heap.add(arr[i])
        }
        val iterator = heap.iterator()
        while (iterator.hasNext()) {
            arr[index++] = heap.remove()
        }
        return arr
    }
}

fun main() {
    SortKMessArray().sortKMessedArrayWithHeap(intArrayOf(1, 4, 5, 2, 3, 7, 8, 6, 10, 9), 2).print()
}