package com.blankj.medium._93

import com.blankj.ext.print
import java.util.*

class Solution {

    fun restoreIpAddresses(s: String): List<String> {
        if (s.length !in 4..12) return emptyList()
        val res = mutableListOf<String>()
        val path: Deque<String> = ArrayDeque(4)
        dfs(s, s.length, 0, 4, res, path)
        return res
    }

    private fun dfs(
            s: String,
            length: Int,
            start: Int,
            residue: Int,
            res: MutableList<String>,
            path: Deque<String>
    ) {
        if (start == length) {
            if (residue == 0) {
                res.add(path.joinToString(separator = "."))
            }
            return
        }
        for (i in start..start + 3) {
            if (i >= length) break
            // Though residue is full, length cant be used up
            if (residue * 3 < length - i) continue
            if (isValidIP(s, start, i)) {
                val curIp = s.substring(start, i + 1)
                path.addLast(curIp)
                dfs(s, length, i + 1, residue - 1, res, path)
                path.removeLast()
            }
        }
    }

    private fun isValidIP(s: String, start: Int, end: Int): Boolean {
        val length = end - start + 1
        // leading zeros
        if (length > 1 && s[start] == '0') return false
        var res = 0
        var start = start
        // Avoid string op
        while (start <= end) {
            res = res * 10 + (s[start] - '0')
            start++
        }
        return res in 0..255
    }
}

fun main() {
    Solution().restoreIpAddresses("25525511135").print()
    Solution().restoreIpAddresses("0000").print()
    Solution().restoreIpAddresses("1111").print()
    Solution().restoreIpAddresses("101023").print()
}