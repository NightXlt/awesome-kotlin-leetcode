package com.blankj.sort

/**
 *  Time: O(n^2)
 *  Memory: O(1)
 *  Stable: No
 *  Method: Exchanging
 */

class BubbleSort {
    private fun bubbleSort(array: IntArray): IntArray? {
        for (i in 0 until array.size - 1) {
            var flag = false // 是否发生交换。没有交换，提前跳出外层循环
            for (j in 0 until array.size - 1 - i) { // 每一轮找到倒数第「i + 1」最大的元素沉底
                if (array[j] > array[j + 1]) {
                    array[j] = array[j + 1].also { array[j + 1] = array[j] }
                    flag = true
                }
            }
            if (!flag) break
        }
        return array
    }
}