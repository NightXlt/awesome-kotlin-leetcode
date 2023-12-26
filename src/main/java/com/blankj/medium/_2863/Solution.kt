package com.blankj.medium._2863

import com.blankj.ext.print
import com.blankj.structure.ListNode
import java.util.TreeMap
import kotlin.math.max

class Solution {
    fun maxSubarrayLength(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        val map = TreeMap<Int, Int>()
        var res = 0
        map[nums[0]] = 0
        for (i in 1..<nums.size) {
            val higherKey = map.higherKey(nums[i])
            if (higherKey != null) {
                res = max(res, i - map.getValue(higherKey) + 1)
            }
            if (nums[i] > map.lastKey()) {
                map[nums[i]] = i
            }
        }
        return res
    }
}