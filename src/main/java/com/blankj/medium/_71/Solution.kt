package com.blankj.medium._71

import com.blankj.ext.print
import java.util.*
import kotlin.collections.ArrayDeque


class Solution {
    fun simplifyPath(path: String): String {
        val strings: List<String> = path.split("/")
        val stack = ArrayDeque<String>()
        for (i in strings.indices) {
            if ("." == strings[i] || "" == strings[i]) continue
            if (".." == strings[i]) {
                if (stack.isNotEmpty()) {
                    stack.removeLast()
                }
            } else {
                stack.add(strings[i])
            }
        }
        val sb = StringBuilder()
        while (stack.isNotEmpty()) {
            sb.insert(0, stack.removeLast())
            sb.insert(0, '/')
        }
        if (sb.isEmpty()) {
            sb.append('/')
        }
        return sb.toString()
    }
}

fun main() {
    Solution().simplifyPath("/../").print()
    Solution().simplifyPath("//.././").print()
}