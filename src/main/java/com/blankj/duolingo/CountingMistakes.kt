package com.blankj.duolingo

import com.blankj.ext.print

class CountingMistakes {
    fun countingMistakes(submissions: List<List<String>>): List<String> {
        val res = mutableListOf<Pair<String, Int>>()
        for ((index, s) in submissions.first().withIndex()) {
            val map = mutableMapOf<String, Int>().withDefault { 0 }
            map[s] = map.getValue(s) + 1
            for (i in 1..<submissions.size) {
                map.merge(submissions[i][index], 1, Integer::sum)
            }
            val sortedList = map.asSequence().sortedBy {
                it.value
            }.toList()
            if (sortedList.size == 1) continue
            val mostCommonMistakes = sortedList.lastOrNull { it.value < sortedList.last().value } ?: continue
            res.addAll(sortedList.filter { it.value == mostCommonMistakes.value }.map { it.key to it.value })
        }
        res.sortWith(compareBy({ -it.second }, { it.first }))
        return res.map { it.first }
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