package com.blankj.medium._97

import com.blankj.ext.print

class Solution {
    // 状态方程 dp[i][j] 表示 s1[0..i] 和 s2[0..j] 能不能交织成 s3[0..i+j+1]
    /**              dp[i-1][j]       s1[i] == s3[i+j+1]
     * dp[i][j] =
     *               dp[i][j - 1]     s2[j] == s3[i + j + 1]
     * 当 i == 0 或者 j == 0 时, i - 1 或者 j - 1 == -1, 意味着当其中一方变为空串了,只剩另一个数组,
     * 这时的 dp[i][j] 就取决于 非空的数组下标元素是否于 s3 下标相等的同时, 以及前一位 dp[i - 1][j - 1] 的值
     * 因为实际数据结构不存在 -1 下标, 故整体数组需要右移一格
     */
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.length + s2.length != s3.length) return false
//        因为数组不支持表示 -1 下标, 故整体右移一格
        val dp = Array(s1.length + 1) { BooleanArray(s2.length + 1) }
        dp[0][0] = true
        for (i in s1.indices) {
            dp[i + 1][0] = s1[i] == s3[i] && dp[i][0]
        }
        for (i in s2.indices) {
            dp[0][i + 1] = s2[i] == s3[i] && dp[0][i]
        }
        for (i in s1.indices) {
            for (j in s2.indices) {
                val ch1 = s1[i]
                val ch2 = s2[j]
                val ch3 = s3[i + j + 1]
                dp[i + 1][j + 1] = (ch1 == ch3 && dp[i][j + 1])
                        || (ch2 == ch3 && dp[i + 1][j])
            }
        }
        return dp[s1.length][s2.length]
    }


    fun isInterleaveWithLessSpace(s1: String, s2: String, s3: String): Boolean {
        if (s1.length + s2.length != s3.length) return false
//        因为数组不支持表示 -1 下标, 故整体右移一格
        if (s1.length < s2.length) return isInterleaveWithLessSpace(s2, s1, s3)
        val dp = BooleanArray(s2.length + 1)
        dp[0] = true

        for (i in s2.indices) {
            dp[i + 1] = s2[i] == s3[i] && dp[i]
        }
        // 二维转一维后, 逐行遍历并更新 dp 数组的值
        // dp[j] 相当于之前的 f(j, j)
        for (i in s1.indices) {
            dp[0] = s1[i] == s3[i] && dp[0]
            for (j in s2.indices) {
                val ch1 = s1[i]
                val ch2 = s2[j]
                val ch3 = s3[i + j + 1]
                dp[j + 1]= (ch1 == ch3 && dp[j + 1])
                        || (ch2 == ch3 && dp[j])
            }
        }
        return dp[s2.length]
    }
}

fun main() {
    Solution().isInterleave(
        "aabcc",
        "dbbca",
        "aadbbcbcac"
    ).print()
    Solution().isInterleave(
        "aabcc", "dbbca", "aadbbbaccc"
    ).print()
    Solution().isInterleave(
        "", "", ""
    ).print()
}