package com.blankj.easy._190

import com.blankj.ext.print

class Solution {
    // Time Complexity: O(n)
    // Space complexity: O(1)
    // you need treat n as an unsigned value
    fun reverseBits(n: Int): Int {
        var res = 0
        var cur = n
        for (i in 0..<32) {
            if (cur == 0) break
            res = res or ((cur and 1) shl (31 - i))
            cur = cur ushr 1
        }
        return res
    }

    fun reverseBitsWithLessTime(n: Int): Int {
        var n = n.toLong()
        n = ((n and 0xffff0000) ushr 16) or ((n and 0x0000ffff) shl 16)
        n = ((n and 0xff00ff00) ushr 8) or ((n and 0x00ff00ff) shl 8)
        n = ((n and 0xf0f0f0f0) ushr 4) or ((n and 0x0f0f0f0f) shl 4)
        n = ((n and 0xcccccccc) ushr 2) or ((n and 0x33333333) shl 2)
        n = ((n and 0xaaaaaaaa) ushr 1) or ((n and 0x55555555) shl 1)
        return n.toInt()
    }
}

