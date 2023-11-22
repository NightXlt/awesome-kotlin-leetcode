package com.blankj.medium._347

import com.blankj.ext.print
import java.util.PriorityQueue

class Solution {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        if (k > nums.size) return intArrayOf()
        val map = mutableMapOf<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        val queue = PriorityQueue<Map.Entry<Int, Int>> { o1, o2 -> o1.value - o2.value }
        for (entry in map) {
            if (queue.size < k) {
                queue.add(entry)
            }else if (entry.value > queue.peek().value) {
                queue.poll()
                queue.offer(entry)
            }
        }
        return queue.map {
            it.key
        }.toIntArray()
    }
}

fun main() {
    Solution().topKFrequent(intArrayOf(1,1,1,2,2,3), 2).print()
    Solution().topKFrequent(intArrayOf(1), 1).print()

    PriorityQueue<Int>().run {
        add(4)
        add(2)
        add(8)
        add(6)
        poll().print()
        poll().print()
        poll().print()
        poll().print()
    }
}