package com.blankj.hard._044
// https://nightxlt.github.io/2020/04/04/REMatch/
class RegexMatch {

    fun isMatch(s: String, p: String): Boolean {
        // length + 1： match empty string
        val dp = Array(s.length + 1) {
            Array(p.length + 1) {
                false
            }
        }
        // "" == ""
        dp[s.length][p.length] = true
        // i from s.length is for empty string match p
        for (i in dp.indices.reversed()) {
            for (j in p.length - 1 downTo 0) {
                //  i < s.length: prevent OOB
                val curMatch = i < s.length && (s[i] == p[j] || p[j] == '.')
                // j + 1 < p.length: prevent OOB
                dp[i][j] = if (j + 1 < p.length && p[j + 1] == '*') {
                    dp[i][j + 2] || (curMatch && dp[i + 1][j])
                } else {
                    curMatch && dp[i + 1][j + 1]
                }
            }
        }
        return dp[0][0]
    }
}

fun main() {
    println(RegexMatch().isMatch("mississippi", "mis*is*p*.*"))
    println(RegexMatch().isMatch("", "mis*is*p*.*"))
    println(RegexMatch().isMatch("", ".*"))
    println(RegexMatch().isMatch("", " "))
}