package com.blankj.medium._752

import com.blankj.ext.print


class Solution {
    fun openLock(deadends: Array<String>, target: String): Int {
        val dead = deadends.toSet()
        val init = "0000"
        if (dead.contains(target) || dead.contains(init)) return -1
        var steps = 0
        var queue1 = ArrayDeque<String>()
        // queue2 的目的是记录下一层级的所有节点，等到上一层级访问结束后再访问 queue2 存储的元素
        var queue2 = ArrayDeque<String>()
        val visited = mutableSetOf<String>()
        queue1.add(init)
        visited.add(init)
        while (queue1.isNotEmpty()) {
            val cur = queue1.removeFirst()
            // if we find target, return steps
            if (cur == target) return steps
            val neighbors = getNeighbors(cur)
            for (neighbor in neighbors) {
                if (!dead.contains(neighbor) && !visited.contains(neighbor)) {
                    queue2.add(neighbor)
                    visited.add(neighbor)
                }
            }
            if (queue1.isEmpty()) {
                steps++
                queue1 = queue2
                queue2 = ArrayDeque()
            }
        }
        return -1
    }

    private fun getNeighbors(cur: String): List<String> {
        val neighbors = mutableListOf<String>()
        for ((i, c) in cur.withIndex()) {
            var builder = StringBuilder(cur)
            var newChar = if (c == '0') '9' else (c - 1)
            builder[i] = newChar
            neighbors.add(builder.toString())
            builder = StringBuilder(cur)
            newChar = if (c == '9') '0' else (c + 1)
            builder[i] = newChar
            neighbors.add(builder.toString())
        }
        return neighbors
    }
}

fun main() {
    Solution().openLock(arrayOf("0000"), "0201").print()
    Solution().openLock(arrayOf("0201"), "0201").print()
    Solution().openLock(arrayOf("0201","0101","0102","1212","2002"), "0202").print()
    Solution().openLock(arrayOf("8888"), "0009").print()
    Solution().openLock(arrayOf("8887","8889","8878","8898","8788","8988","7888","9888"), "8888").print()
}