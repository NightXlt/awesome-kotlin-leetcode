package com.blankj.hard._010

class RegexMatchWithSerialOrder {
    fun isMatch(s: String, p: String): Boolean {
        // length + 1： match empty string
        val m = s.length + 1
        val n = p.length + 1
        val dp = Array(m) { Array(n) { false } }
        dp[0][0] = true
        for (i in 2..<n step 2) {
            dp[0][i] = dp[0][i - 2] && p[i - 1] == '*'
        }
        // i from s.length is for empty string match p
        for (i in 1..<m) {
            for (j in 1..<n) {
                dp[i][j] = if (p[j - 1] == '*') {
                    dp[i][j - 2] || (dp[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.'))
                } else {
                    dp[i - 1][j - 1] && (s[i - 1] == p [j - 1] || p[j - 1] == '.')
                }
            }
        }
        return dp.last().last()
    }

}

fun main() {
    println(RegexMatchWithSerialOrder().isMatch("mississippi", "mis*is*p*.*"))
    println(RegexMatchWithSerialOrder().isMatch("", "mis*is*p*.*"))
    println(RegexMatchWithSerialOrder().isMatch("", ".*"))
    println(RegexMatchWithSerialOrder().isMatch("", " "))
}