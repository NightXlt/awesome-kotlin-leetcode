package com.blankj.medium._921

import com.blankj.ext.print
import com.blankj.structure.TreeNode


class Solution {

    fun minAddToMakeValid(s: String): Int {
        if (s.isEmpty()) return 0
        val stack = ArrayDeque<Char>()
        var res = 0
        for (c in s) {
            when (c) {
                '(' -> stack.add(c)
                ')' -> {
                    if (stack.isEmpty()) {
                        res++
                        continue
                    }
                    val topElement = stack.removeLast()
                    if (topElement != '(') {
                        res++
                    }
                }
            }
        }
        res += if (stack.isEmpty()) 0 else stack.size
        return res
    }
}

fun main() {
    Solution().minAddToMakeValid("())").print()
    Solution().minAddToMakeValid("(((").print()
}