package com.blankj.medium._128

import com.blankj.ext.print
import kotlin.math.max

class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val map = mutableMapOf<Int, Int>()
        var low: Int
        var high: Int
        var sum: Int
        var maxLength = 0
        for (num in nums) {
            if (map.contains(num)) continue
            low = map[num - 1] ?: 0
            high = map[num + 1] ?: 0
            sum = low + high + 1
            maxLength = max(sum, maxLength)
            map[num - low] = sum
            map[num + high] = sum
            map[num] = sum
        }
        return maxLength
    }

    private fun merge(find: MutableMap<Int, Int>, x: Int, y: Int, count: MutableMap<Int, Int>): Boolean {
        val fx = find(find, x)
        val fy = find(find, y)
        if (fx != fy) {
            find[fx] = fy
            count[fy] = count[fx]!! + count[fy]!!
            return true
        }
        return false
    }

    private fun find(find: MutableMap<Int, Int>, x: Int): Int {
        var index = x
        while (find[index] != index) {
            index = find[index]!!
        }
        return index
    }

    fun longestConsecutiveUnionFind(nums: IntArray): Int {
        val find = mutableMapOf<Int, Int>()
        val count = mutableMapOf<Int, Int>()
        val all = mutableSetOf<Int>()
        for (num in nums) {
            find[num] = num
            count[num] = 1
            all.add(num)
        }
        for (num in nums) {
            if (all.contains(num - 1)) {
                merge(find, num, num - 1, count)
            }
            if (all.contains(num + 1)) {
                merge(find, num, num + 1, count)
            }
        }
        return count.values.maxOrNull() ?: 0
    }
}

fun main() {
    Solution().longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2)).print()
    Solution().longestConsecutive(intArrayOf(1)).print()
    Solution().longestConsecutive(intArrayOf(1, 5, 4, 2, 3)).print()
}