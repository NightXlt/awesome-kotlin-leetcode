package com.blankj.medium._93

import com.blankj.ext.print

class Solution {

    fun restoreIpAddresses(s: String): List<String> {
        if (s.length !in 4..12) return emptyList()
        val res = mutableListOf<String>()
        val path = ArrayDeque<String>()
        dfs(s, 0, 4, res, path)
        return res
    }

    private fun dfs(s: String, start: Int, residual: Int, res: MutableList<String>, path: ArrayDeque<String>) {
        if (start == s.length) {
            if (residual == 0) {
                res.add(path.joinToString(separator = "."))
            }
            return
        }
        for (i in start..start + 3) {
            if (i >= s.length) break
            if (residual * 3 + i < s.length) continue
            if (!isValidIP(s, start, i)) continue
            val ip = s.substring(start, i + 1)
            path.add(ip)
            dfs(s, i + 1, residual - 1, res, path)
            path.removeLast()
        }
    }

    private fun isValidIP(s: String, start: Int, end: Int): Boolean {
        val length = end - start + 1
        // prefix zero
        if (length > 1 && s[start] == '0') return false
        var result = 0
        for (i in start..end) {
            result = result * 10 + (s[i] - '0')
        }
        return result in 0..255
    }


}

fun main() {
    Solution().restoreIpAddresses("25525511135").print()
    Solution().restoreIpAddresses("0000").print()
    Solution().restoreIpAddresses("1111").print()
    Solution().restoreIpAddresses("101023").print()
}