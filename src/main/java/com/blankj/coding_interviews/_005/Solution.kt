package com.blankj.coding_interviews._005

import com.blankj.ext.print

class Solution {
    fun replaceSpace(s: String): String {
        if (s.isEmpty()) return s
        val spaceCount = s.count { it == ' ' }
        val newLen = s.length + spaceCount * 2
        val char = CharArray(newLen)
        var lastIndex = newLen - 1
        for (index in s.lastIndex downTo 0) {
            if (s[index] == ' ') {
                char[lastIndex--] = '0'
                char[lastIndex--] = '2'
                char[lastIndex--] = '%'
            } else {
                char[lastIndex--] = s[index]
            }
        }
        return String(char, 0, newLen)
    }

    fun replaceSpaceSB(s: String): String {
        if (s.isEmpty()) return s
        val stringBuilder = StringBuilder()
        s.forEach {
            if (it == ' ') {
                stringBuilder.append("%20")
            } else {
                stringBuilder.append(it)
            }
        }
        return stringBuilder.toString()
    }
}

fun main() {
    // test cases
    Solution().replaceSpaceSB("").print() // empty string
    Solution().replaceSpaceSB(" ").print() // one space
    Solution().replaceSpaceSB("    ").print() // multi space
    Solution().replaceSpaceSB("We are happy.").print() // single space
    Solution().replaceSpaceSB("We are  happy.").print() // double space
    Solution().replaceSpaceSB("Wearehappy.").print() // no space
}