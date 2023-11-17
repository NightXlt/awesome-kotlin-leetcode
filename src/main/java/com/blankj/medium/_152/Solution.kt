package com.blankj.medium._152

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) throw IllegalArgumentException("Empty array")
        var maxF = nums[0]
        var minF = nums[0]
        var res = maxF
        for (i in 1..nums.lastIndex) {
            val mx = maxF
            val mn = minF
            maxF = max(mx * nums[i], max(mn * nums[i], nums[i]))
            minF = min(mx * nums[i], min(mn * nums[i], nums[i]))
            res = max(res, maxF)
        }
        return res
    }

    fun maxProduct(words: Array<String>): Int {
        val flags = IntArray(words.size)
        for (i in words.indices) {
            for (j in words[i].indices) {
                flags[i] = flags[i] or (1 shl (words[i][j] - 'a'))
            }
        }
        var res = 0
        for (i in words.indices) {
            for (j in i + 1 until words.size) {
                if (flags[i] and flags[j] == 0 && words[i].length * words[j].length > res) {
                    res = words[i].length * words[j].length
                }
            }
        }
        return res
    }
}

fun main() {
    Solution().maxProduct(intArrayOf(2, 3, -2, 4)).print()
    Solution().maxProduct(intArrayOf(-2, 0, -1)).print()
    Solution().maxProduct(intArrayOf())
}
