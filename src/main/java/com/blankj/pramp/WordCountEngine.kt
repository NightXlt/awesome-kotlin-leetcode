package com.blankj.pramp

import java.util.*


class WordCountEngine {
    fun wordCountEngine(document: String): Array<Array<String?>> {
        // [^] means not
        val cleanedDocument =
            document.lowercase().replace("[^a-z ]".toRegex(), "") // Remove non-alphabetic characters
        val words = cleanedDocument.split(" ")
        val wordCounts: MutableMap<String, Int> = LinkedHashMap()
        for (word in words) {
            if (word.isNotEmpty()) {
                wordCounts.merge(word, 1, Integer::sum)
            }
        }

        // Sort entries by count in descending order, preserving original order for equal counts
        val sortedEntries: List<Map.Entry<String, Int>> = ArrayList<Map.Entry<String, Int>>(wordCounts.entries)
        sortedEntries.sortedBy { -it.value }
        val result = Array(sortedEntries.size) {
            arrayOfNulls<String>(
                2
            )
        }
        var i = 0
        for ((key, value) in sortedEntries) {
            result[i][0] = key
            result[i][1] = value.toString()
            i++
        }
        return result
    }
}