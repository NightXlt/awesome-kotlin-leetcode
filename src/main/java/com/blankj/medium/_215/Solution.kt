package com.blankj.medium._215

import com.blankj.ext.print
import java.util.*

class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        if (k !in 1..nums.size) return -1
        val priorityQueue = PriorityQueue<Int>() { o1, o2 -> o2 - o1 }
        for (num in nums) {
            priorityQueue.add(num)
        }
        repeat (k - 1){ priorityQueue.poll() }
        return priorityQueue.poll()
    }
}