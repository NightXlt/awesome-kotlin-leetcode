package com.blankj.medium._421

import com.blankj.ext.print
import kotlin.math.max

class Trie {

    var children = HashMap<Int, Trie>()
        private set

    /** Inserts a word into the trie. */
    fun insert(nums: IntArray) {
        for (num in nums) {
            var cur = this
            for (i in 31 downTo 0) {
                val bit = (num shr i) and 1
                cur = cur.children.getOrPut(bit) { Trie() }
            }
        }
    }
}

class Solution {
    fun findMaximumXOR(nums: IntArray): Int {
        var mask = 0
        var res = 0
        for (i in 31 downTo 0) {
            mask = mask or (1 shl i)
            val set = HashSet<Int>()
            for (num in nums) {
                set.add(num and mask)
            }
            val t = res or (1 shl i)
            for (prefix in set) {
                if (set.contains(prefix xor t)) {
                    res = t
                }
            }
        }
        return res
    }

    fun findMaximumXORWithTrie(nums: IntArray): Int {
        val trie = Trie()
        trie.insert(nums)
        var max = 0
        for (num in nums) {
            var cur = trie
            var xor = 0
            // 贪心的匹配高位的 1
            for (i in 31 downTo 0) {
                val bit = (num shr i) and 1
                if (1 - bit in cur.children) {
                    xor = (xor shl 1) + 1
                    cur = cur.children[1 - bit]!!
                } else {
                    xor = xor shl 1
                    cur = cur.children[bit]!!
                }
            }
            max = max(max, xor)
        }
        return max
    }
}

fun main() {
    Solution().findMaximumXOR(intArrayOf(3, 10, 5, 25, 2, 8)).print()
}