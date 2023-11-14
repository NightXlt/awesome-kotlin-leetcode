package com.blankj.medium._792

import com.blankj.ext.print

class Solution {
    fun numMatchingSubseq(s: String, words: Array<String>): Int {
        if (words.any { it.isNotEmpty() && it.length > s.length }) return 0
        if (words.any { it.any { !it.isLowerCase() } }) return -1
        val queues = Array<ArrayDeque<IntArray>>(26) { ArrayDeque() }
        // queues 存下 26 个字母的队列， 这样每次匹配 s 时，就能所有 words 一起移动了
        for (i in words.indices) {
            queues[(words[i][0] - 'a')].add(intArrayOf(i, 0))
        }
        var res = 0
        for (c in s) {
            val queue = queues[c - 'a']
            val size = queue.size
            repeat(size) {
                val t = queue.removeFirst()
                if (t[1] == words[t[0]].length - 1) {
                    res++
                } else {
                    t[1]++
                    queues[words[t[0]][t[1]] - 'a'].add(t)
                }
            }
        }
        return res
    }
}

fun main() {
    Solution().numMatchingSubseq("abcde", arrayOf("a","bb","acd","ace")).print()
    Solution().numMatchingSubseq("dsahjpjauf", arrayOf("ahjpjau","ja","ahbwzgqnuk","tnmlanowax")).print()
}