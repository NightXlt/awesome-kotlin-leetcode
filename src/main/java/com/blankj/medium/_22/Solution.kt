package com.blankj.medium._22

import com.blankj.ext.print

class Solution {
    fun generateParenthesis(n: Int): List<String> {
        val res = mutableListOf<String>()
        fun dfs(left: Int, right: Int, curString: String) {
            if (curString.length == 2 * n) {
                res.add(curString)
                return
            }
            if (left < n) {
                dfs(left + 1, right, "$curString(")
            }
            if (right < left) {
                dfs(left, right + 1, "$curString)")
            }
        }
        dfs(0, 0, "")
        return res
    }
}

fun main() {
    Solution().generateParenthesis(3).print()
}