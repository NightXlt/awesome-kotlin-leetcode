package com.blankj.easy._028

import com.blankj.ext.print

class StrStr {
    fun strStr(ss: String, pp: String): Int {
        val n = ss.length
        val m = pp.length
        val s = ss.toCharArray()
        val p = pp.toCharArray()
        // 枚举原串的「发起点」
        for (i in 0..n - m) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            var a = i
            var b = 0
            while (b < m && s[a] == p[b]) {
                a++
                b++
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i
        }
        return -1
    }


    fun strStrWithKmp(haystack: String, needle: String): Int {
        if (haystack.length < needle.length) {
            return -1
        }
        if (haystack.isEmpty() && haystack == needle) {
            return 0
        }
        val next = initNext(needle)
        var i = 1
        var j = 1
        while (i <= haystack.length && j <= needle.length) {
            if (j == 0 || haystack[i - 1] == needle[j - 1]) {
                i++
                j++
            } else {
                j = next[j]
            }
        }
        if (j > needle.length) {
            return i - needle.length - 1
        }
        return -1
    }

    private fun initNext(needle: String): IntArray {
        if (needle.isBlank()) {
            return  IntArray(1)
        }
        val next = IntArray(needle.length + 1)
        var i = 1
        var j = 0
        while (i < needle.length) {
            if (j == 0 || needle[i - 1] == needle[j - 1]) {
                next[++i] = ++j
            } else {
                j = next[j] //不相等时匹配串滑到对应位置 next[j],j - 1处元素继续和 i-1处元素进行比较
            }
        }
        return next
    }
}

fun main() {
    StrStr().strStrWithKmp("aaasabcabac", "abcabac").print()
    StrStr().strStrWithKmp("aaasdsdfsdfsdf", "abcabac").print()
}