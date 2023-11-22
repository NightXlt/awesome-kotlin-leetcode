package com.blankj.easy._703

import com.blankj.ext.print
import java.util.PriorityQueue

class KthLargest(val k: Int, nums: IntArray) {
    val queue = PriorityQueue<Int>()

    init {
        for (num in nums) {
            add(num)
        }
    }

    fun add(`val`: Int): Int {
        if (queue.size < k) {
            queue.add(`val`)
        } else if (`val` > queue.peek()){
            queue.poll()
            queue.offer(`val`)
        }
        return queue.peek()
    }

}

fun main() {
    val kthLargest =  KthLargest(3, intArrayOf(4, 5, 8, 2));
    kthLargest.add(3).print();   // return 4
    kthLargest.add(5).print();   // return 5
    kthLargest.add(10).print();  // return 5
    kthLargest.add(9).print();   // return 8
    kthLargest.add(4).print();   // return 8
}