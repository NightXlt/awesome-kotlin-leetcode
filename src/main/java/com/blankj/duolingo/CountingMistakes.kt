package com.blankj.duolingo

import com.blankj.ext.print

/**
 * Time Complexity: O(n * m + m * log(m))
 * Space Complexity: O(n * m)
 * Here, n represents the number of submissions, and m represents the number of mistakes (columns).
 */
class CountingMistakes {
    fun countingMistakes(submissions: List<List<String>>): List<String> {
        if (submissions.size <= 1) return emptyList()
        val res = mutableListOf<Map.Entry<String, Int>>()
        for ((index, s) in submissions.first().withIndex()) {
            val map = mutableMapOf<String, Int>().withDefault { 0 }
            map[s] = map.getValue(s) + 1
            for (i in 1..<submissions.size) {
                map.merge(submissions[i][index], 1, Integer::sum)
            }
            var correctWordCount = Int.MIN_VALUE
            var mostCommonMistakes = Int.MIN_VALUE
            for (count in map.values) {
                if (count > correctWordCount) {
                    mostCommonMistakes = correctWordCount
                    correctWordCount = count
                } else if (count > mostCommonMistakes && count != correctWordCount) {
                    mostCommonMistakes = count
                }
            }
            if (mostCommonMistakes == Int.MIN_VALUE) continue
            //val mostCommonMistakes = sortedList.lastOrNull { it.value < sortedList.last().value } ?: continue
            res.addAll(map.filter { it.value == mostCommonMistakes }.entries)
        }
        res.sortWith(compareBy({ -it.value }, { it.key }))
        return res.map { it.key }
    }
}

fun main() {
    CountingMistakes().countingMistakes(
        listOf(
            listOf("your", "bear", "drinks", "beer"),
            listOf("your", "bear", "eats", "beer"),
            listOf("yuor", "bear", "drinks", "beer"),
            listOf("your", "bear", "drinks", "beer"),
            listOf("yuor", "bear", "drinks", "bear"),
        )
    ).print()
    CountingMistakes().countingMistakes(
        listOf(
            listOf("we", "run", "to", "mars"),
            listOf("they", "run", "to", "venus"),
            listOf("we", "walk", "to", "mars"),
            listOf("they", "mars", "to", "run"),
        )
    ).print()
}