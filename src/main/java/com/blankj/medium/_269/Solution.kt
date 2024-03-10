package com.blankj.medium._269

import com.blankj.ext.print


class Solution {
    fun alienOrder(words: Array<String>): String {
        if (words.isEmpty()) return ""
        // graph stores outDegree's chars(value) of char(key)
        val graph = mutableMapOf<Char, MutableSet<Char>>()
        val inDegrees = mutableMapOf<Char, Int>()
        initGraph(words, graph, inDegrees)
        for (i in 1 until words.size) {
            val w1 = words[i - 1]
            val w2 = words[i]
            // 如果 star 排在了 sta 前面肯定是有问题的， 直接返回空串。
            if (w1.startsWith(w2) && w1.length != w2.length) return ""
            var j = 0
            while (j < w1.length && j < w2.length) {
                val c1 = w1[j]
                val c2 = w2[j]
                if (c1 != c2) {
                    if (!graph[c1]!!.contains(c2)) {
                        graph[c1]!!.add(c2)
                        inDegrees[c2] = inDegrees[c2]!! + 1
                    }
                    break
                }
                j++
            }
        }
        val builder = bfs(inDegrees, graph)
        return if (builder.length == graph.size) builder.toString() else ""
    }

    private fun initGraph(
        words: Array<String>,
        graph: MutableMap<Char, MutableSet<Char>>,
        inDegrees: MutableMap<Char, Int>
    ) {
        for (word in words) {
            for (c in word.toCharArray()) {
                graph.putIfAbsent(c, mutableSetOf())
                inDegrees.putIfAbsent(c, 0)
            }
        }
    }

    private fun bfs(
        inDegrees: MutableMap<Char, Int>,
        graph: MutableMap<Char, MutableSet<Char>>
    ): StringBuilder {
        val queue = ArrayDeque<Char>()
        queue.addAll(inDegrees.keys.filter { inDegrees[it] == 0 })
        val builder = StringBuilder()
        while (queue.isNotEmpty()) {
            val ch = queue.removeFirst()
            builder.append(ch)
            for (neighbor in graph[ch]!!) {
                inDegrees[neighbor] = inDegrees[neighbor]!! - 1
                if (inDegrees[neighbor] == 0) {
                    queue.add(neighbor)
                }
            }
        }
        return builder
    }
}


fun main() {
    Solution().alienOrder(arrayOf("wrt","wrf","er","ett","rftt")).print()
}