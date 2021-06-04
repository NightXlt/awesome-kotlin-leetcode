package com.blankj.hard._127

import com.blankj.coding_interviews._004.print
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max

class Solution {

    var count = 0

    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val dict = HashSet(wordList)
        if (!dict.contains(endWord)) {
            return 0
        }
        val begin = HashSet<String>()
        begin.add(beginWord)
        val end = HashSet<String>()
        end.add(endWord)
        val map = HashMap<String, MutableList<String>>()
        count = 1
        if (!doubleBfs(dict, begin, end, map, true)) {
            count = 0
        }
        return count
    }

    fun doubleBfs(dict: HashSet<String>, begin: Set<String>, end: Set<String>, map: HashMap<String, MutableList<String>>, isTopDown: Boolean): Boolean {
        if (begin.isEmpty()) {
            return false
        }
        if (begin.size > end.size) {
            return doubleBfs(dict, end, begin, map, !isTopDown)
        }
        dict.removeAll(begin)
        dict.removeAll(end)
        var isTraversalEnd = false //是否遍历结束
        val visited = HashSet<String>()
        count++
        for (word in begin) {
            val chars = word.toCharArray()
            for (i in chars.indices) {
                val temp = chars[i]
                for (c in 'a'..'z') {
                    if (c == temp) {
                        continue
                    }
                    chars[i] = c
                    val neighborWord = String(chars)
                    val key = if (isTopDown) word else neighborWord
                    val value = if (isTopDown) neighborWord else word
                    val neighborWords = map.getOrDefault(key, mutableListOf())

                    if (end.contains(neighborWord)) {
                        isTraversalEnd = true
                        neighborWords.add(value)
                        map[key] = neighborWords
                    }
                    if (isTraversalEnd) return true
                    if (!dict.contains(neighborWord)) {
                        continue
                    }
                    visited.add(neighborWord)
                    neighborWords.add(value)
                    map[key] = neighborWords
                }
                chars[i] = temp
            }
        }
        return doubleBfs(dict, visited, end, map, isTopDown)
    }
}

fun main() {
//    Solution().trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)).print()
//    Solution().trap(intArrayOf(4, 2, 0, 3, 2, 5)).print()
}