package com.blankj.coding_interviews._50

import com.blankj.coding_interviews._004.print


class Solution {
    fun firstUniqChar(s: String): Char {
        if (s.isEmpty()) return ' '
        val dic = LinkedHashMap<Char, Boolean>()
        for (c in s) {
            dic[c] = !dic.containsKey(c)
        }
        dic.forEach { (t, u) ->
            if (u) {
                return t
            }
        }
        return ' '
    }
}

fun main() {
    Solution().firstUniqChar("").print()
    Solution().firstUniqChar("abaccdeff").print()
    Solution().firstUniqChar("aabbcc").print()
    Solution().firstUniqChar("abc").print()
}
