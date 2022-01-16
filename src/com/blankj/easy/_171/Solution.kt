package com.blankj.easy._171

import com.blankj.ext.print

class Solution {
    fun titleToNumber(columnTitle: String): Int {
        if (columnTitle.isEmpty()) return 0
        var res= 0
        for (c in columnTitle) {
            val num = c - 'A' + 1
            res = res * 26 + num
        }
        return res
    }
}

fun main() {
    Solution().titleToNumber("").print()
    Solution().titleToNumber("AB").print()
    Solution().titleToNumber("A").print()
    Solution().titleToNumber("ZY").print()
}