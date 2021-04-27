package com.blankj.coding_interviews._59

import com.blankj.coding_interviews._004.print
import java.util.*

class Solution {
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        // If k is bigger than nums' size, index out of exception would happen.
        if (nums.isEmpty() || k - 1 !in nums.indices) return intArrayOf()
        val res = IntArray(nums.size - k + 1)
        val dequeue: Deque<Int> = LinkedList<Int>()
        // construct first window
        for (i in 0 until k) {
            while (dequeue.isNotEmpty() && dequeue.last < nums[i]) {
                dequeue.removeLast()
            }
            dequeue.add(nums[i])
        }
        res[0] = dequeue.first
        // move window
        for (i in k until nums.size) {
            if (dequeue.first == nums[i - k]) {
                dequeue.removeFirst()
            }
            while (dequeue.isNotEmpty() && dequeue.last < nums[i]) {
                dequeue.removeLast()
            }
            dequeue.add(nums[i])
            res[i - k + 1] = dequeue.first
        }
        return res
    }
}

fun main() {
    Solution().maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 3).print()
    Solution().maxSlidingWindow(intArrayOf(-1, -1, -1, -1, -1), 3).print()
    Solution().maxSlidingWindow(intArrayOf(1), 1).print()
    Solution().maxSlidingWindow(intArrayOf(), 1).print()
    Solution().maxSlidingWindow(intArrayOf(1), 2).print()
}
