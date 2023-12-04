package com.blankj.medium._433

import com.blankj.ext.print

class Solution {
    fun minMutation(startGene: String, endGene: String, bank: Array<String>): Int {
        val validGene = bank.toSet()
        if (endGene !in validGene) {
            return -1
        }
        if (startGene == endGene) {
            return 0
        }
        val visited = mutableSetOf<String>()
        val queue = ArrayDeque<String>()
        queue.add(startGene)
        visited.add(startGene)
        val geneKinds = charArrayOf('A', 'C', 'G', 'T')
        var res = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val cur = queue.removeFirst()
                for (i in 0..<8) {
                    val builder = StringBuilder(cur)
                    for (j in 0..<4) {
                        if (cur[i] == geneKinds[j]) continue
                        builder[i] = geneKinds[j]
                        val str = builder.toString()
                        if (str == endGene) {
                            res++
                            return res
                        }
                        if (str !in visited && str in validGene) {
                            queue.add(str)
                            visited.add(str)
                        }
                    }
                }
            }
            res++
        }
        return -1
    }
}

fun main() {
    Solution().minMutation("AACCGGTT", "AACCGGTA", arrayOf("AACCGGTA")).print()
    Solution().minMutation("AACCGGTT", "AAACGGTA", arrayOf("AACCGGTA", "AACCGCTA", "AAACGGTA")).print()
}