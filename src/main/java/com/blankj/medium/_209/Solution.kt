package com.blankj.medium._209

import com.blankj.ext.print
import java.util.*
import kotlin.math.min


class Solution {

    // O(n)
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        if (nums.isEmpty()) error("Empty array!")
        var minLength = Int.MAX_VALUE
        var i = 0
        var sum = 0
        for (j in nums.indices) {
            sum += nums[j]
            while (i <= j && sum >= target) {
                minLength = min(j - i + 1, minLength)
                sum -= nums[i]
                i++
            }
        }
        return if (minLength == Int.MAX_VALUE) 0 else minLength
    }

    // O(n*log(n))
    fun minSubArrayLenWithPrefixSum(s: Int, nums: IntArray): Int {
        val n = nums.size
        if (n == 0) {
            return 0
        }
        var ans = Int.MAX_VALUE
        val sums = IntArray(n + 1)
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (i in 1..n) {
            sums[i] = sums[i - 1] + nums[i - 1]
        }
        for (i in 1..n) {
            val target = s + sums[i - 1]
            var bound = Arrays.binarySearch(sums, target)
            if (bound < 0) {
                bound = -bound - 1
            }
            if (bound <= n) {
                ans = min(ans, bound - i + 1)
            }
        }
        return if (ans == Int.MAX_VALUE) 0 else ans
    }

}

fun main() {
    Solution().minSubArrayLenWithPrefixSum(7, intArrayOf(2, 3, 1, 2, 4, 3)).print()
}