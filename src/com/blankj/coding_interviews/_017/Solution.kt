package com.blankj.coding_interviews._017

import com.blankj.coding_interviews._004.print

class Solution {

    private var list: MutableList<Int> = mutableListOf()

    fun printNumbers(n: Int): IntArray? {
        if (n <= 0) return intArrayOf()
        dfs(n, 0, StringBuilder())
        return list.toIntArray()
    }

    fun dfs(n: Int, curIndex: Int, str: StringBuilder) {
        if (curIndex == n) {
            val res = str.trimStart('0')
            if (res.isNotEmpty()) {
                list.add(str.toString().toInt())
            }
            return
        }
        for (i in 0..9) {
            str.append(i)
            dfs(n, curIndex + 1, str)
            if (str.isNotEmpty()) {
                str.deleteCharAt(str.lastIndex)
            }
        }
    }
}


fun main() {
    Solution().printNumbers(1)?.print()
    Solution().printNumbers(2)?.print()
    Solution().printNumbers(3)?.print()
    Solution().printNumbers(-1)?.print() // illegal case
    Solution().printNumbers(0)?.print()  // illegal case
}
