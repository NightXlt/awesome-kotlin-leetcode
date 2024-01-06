package com.blankj.hard._127

class Solution {

    private var count = 0

    // 这道题因为不需要记录完整的路径，所以不需要啥 map
    /**
     * TC: O(N*(C^2)) 其中 N 为 wordList 的长度，C 为列表中单词的长度
     * SC: O(N*(C^2)) 其中 N 为 wordList 的长度，C 为列表中单词的长度
     */
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val dict = HashSet(wordList)
        if (!dict.contains(endWord)) {
            return 0
        }
        val begin = HashSet<String>()
        begin.add(beginWord)
        val end = HashSet<String>()
        end.add(endWord)
        count = 1
        if (!doubleBfs(dict, begin, end)) {
            count = 0
        }
        return count
    }

    private fun doubleBfs(unVisited: HashSet<String>, begin: Set<String>, end: Set<String>): Boolean {
        if (begin.isEmpty()) {
            return false
        }
        if (begin.size > end.size) {
            return doubleBfs(unVisited, end, begin)
        }
        unVisited.removeAll(begin)
        unVisited.removeAll(end)
        val validNeibors = HashSet<String>()
        count++
        for (word in begin) {
            val neighbors: List<String> = getNeighbors(word)
            for (neighborWord in neighbors) {
                if (end.contains(neighborWord)) {
                    return true
                }
                if (!unVisited.contains(neighborWord)) continue
                validNeibors.add(neighborWord)
            }
        }
        return doubleBfs(unVisited, validNeibors, end)
    }

    private fun getNeighbors(word: String): List<String> {
        val builder = StringBuilder(word)
        val res = mutableListOf<String>()
        for ((i, c) in word.withIndex()) {
            for (newChar in 'a'..'z') {
                if (newChar == c) continue
                builder[i] = newChar
                res.add(builder.toString())
            }
            builder[i] = c
        }
        return res
    }

}

fun main() {
//    Solution().trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)).print()
//    Solution().trap(intArrayOf(4, 2, 0, 3, 2, 5)).print()
}