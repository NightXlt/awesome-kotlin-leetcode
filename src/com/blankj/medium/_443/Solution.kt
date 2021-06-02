package com.blankj.medium._443

import com.blankj.coding_interviews._004.print

class Solution {

    // 双指针扫描思想
    fun compress(chars: CharArray): Int {
        if (chars.size <= 1) return chars.size
        var left = 0
        var size = 0
        for (right in chars.indices) {
            if (right + 1 == chars.size || chars[right] != chars[right + 1]) {
                chars[size++] = chars[right]
                if (right > left) {
                    for (c in (right - left + 1).toString()) {
                        chars[size++] = c
                    }
                }
                left = right + 1
            }
        }
        return size
    }
}

fun main() {

    Solution().compress(
            charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')
    ).print()
}