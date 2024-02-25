package com.blankj.medium._318

import com.blankj.ext.print
import kotlin.math.max
import kotlin.math.min

class Solution {
    fun maxProduct(words: Array<String>): Int {
        val flags = IntArray(words.size)
        for (i in words.indices) {
            for (j in words[i].indices) {
                flags[i] = flags[i] or (1 shl (words[i][j] - 'a'))
            }
        }
        var res = 0
        for (i in words.indices) {
            for (j in i + 1 until words.size) {
                if (flags[i] and flags[j] == 0 && words[i].length * words[j].length > res) {
                    res = words[i].length * words[j].length
                }
            }
        }
        return res
    }
}

