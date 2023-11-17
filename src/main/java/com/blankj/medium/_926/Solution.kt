package com.blankj.medium._926

import com.blankj.ext.print
import kotlin.math.min


class Solution {
    fun minFlipsMonoIncr(s: String): Int {
        if (s.isEmpty()) return 0
        val dp = Array(2) { IntArray(2) }
        dp[0][0] = if (s[0] == '0') 0 else 1
        dp[1][0] = if (s[0] == '1') 0 else 1

        for (i in 1 until s.length) {
            val prev0 = dp[0][(i - 1) % 2]
            val prev1 = dp[1][(i - 1) % 2]
            dp[0][i % 2] = prev0 + if (s[i] == '1') 1 else 0
            dp[1][i % 2] = min(prev1, prev0) + if (s[i] == '0') 1 else 0
        }
        return min(dp[0][s.lastIndex % 2], dp[1][s.lastIndex % 2])
    }
}

fun main() {
    Solution().minFlipsMonoIncr("00110").print()
    Solution().minFlipsMonoIncr("010110").print()
    Solution().minFlipsMonoIncr("00011000").print()
}