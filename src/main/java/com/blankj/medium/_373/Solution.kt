package com.blankj.medium._373

import com.blankj.ext.print
import java.util.*
import kotlin.math.min

class Solution {
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        if (k < 0) error("Illegal input: k($k)")
        // max heap
        val queue = PriorityQueue<Pair<Int, Int>> { p1, p2 ->
            p2.sum() - p1.sum()
        }
        for (i in 0..<min(k, nums1.size)) {
            for (j in 0..<min(k, nums2.size)) {
                if (queue.size < k) {
                    queue.offer(nums1[i] to nums2[j])
                } else {
                    val peekSum = queue.peek().sum()
                    if (peekSum > nums1[i] + nums2[j]) {
                        queue.poll()
                        queue.offer(nums1[i] to nums2[j])
                    }
                }
            }
        }
        return queue.map { it.toList() }.toList()
    }

    fun kSmallestPairsWithMinHeap(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        if (k < 0) error("Illegal input: k($k)")
        if (nums1.isEmpty() || nums2.isEmpty()) return emptyList()
        val minHeap = PriorityQueue<Pair<Int, Int>> { p1, p2 ->
            nums1[p1.first] + nums2[p1.second] - nums1[p2.first] - nums2[p2.second]
        }
        // min heap
        for (i in 0..<min(k, nums1.size)) {
            minHeap.offer(i to 0)
        }
        var k = k
        val res = mutableListOf<List<Int>>()
        while (k > 0 && minHeap.isNotEmpty()) {
            val indexs = minHeap.poll()
            res.add(listOf(nums1[indexs.first], nums2[indexs.second]))
            if (indexs.second < nums2.lastIndex) {
                minHeap.offer(indexs.first to (indexs.second + 1))
            }
            k--
        }
        return res
    }

    fun Pair<Int, Int>.sum(): Int {
        return first + second
    }
}

fun main() {
    Solution().kSmallestPairsWithMinHeap(
        intArrayOf(1, 7, 11),
        intArrayOf(2, 4, 6),
        3
    ).print()
}