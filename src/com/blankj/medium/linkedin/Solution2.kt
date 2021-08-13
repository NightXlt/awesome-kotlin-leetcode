package com.blankj.medium.linkedin

import com.blankj.coding_interviews._004.print

class Solution2 {
    val digitRange = '0'..'9'

    fun isMatching(s: String, p: String): Boolean {
        if (s == p) return true
        var i = 0
        var j = 0
        while (i < s.length && j < p.length) {
            var patternChar = p[j]
            // if pattern char is digit
            if (patternChar in digitRange) {
                val (index, num) = getNum(p, j)
                if (num == -1) return false
                j = index
                repeat(num) { // move 「num - 1」 times i pointor
                    if (i == s.length - 1 && j == p.length - 1) return true
                    if (i == s.length - 1) return false
                    i++
                }
            }
            val matchedChar = s[i]
            patternChar = p[j]
            // current s char isn't equal to p char, so return false directly
            if (patternChar != matchedChar) return false
            i++
            j++
        }
        return i == s.length && j == p.length
    }

    fun getNum(s: String, start: Int): Pair<Int, Int> {
        var left = start
        var res = 0
        while (s[left] in digitRange) {
            val num = s[left] - '0'
            if (num == 0 && left == start) return left to -1
            res = res * 10 + num
            left++
        }
        return left to res
    }
}

fun main() {
    /**
     * test cases
     * 1. s = "", p = "" true
     * 2. s = "apple" p = "a2e" false
     * 3. s = "cake" p = "c2e" true
     * 4. s = "internationalization", p = "i12iz4n", true
     * 4. s = "cake", p = "c0ake", false
     */
    Solution2().isMatching("", "").print()
    Solution2().isMatching("apple", "a2e").print()
    Solution2().isMatching("cake", "c2e").print()
    Solution2().isMatching("internationalization", "i12iz4n").print()
    Solution2().isMatching("cake", "c0ake").print()

}