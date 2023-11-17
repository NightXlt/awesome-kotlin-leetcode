package com.blankj.interview.booking.writte_test

import com.blankj.ext.print

internal object Result {
    /*
     * Complete the 'autoCorrectSuggestions' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING input
     *  2. STRING_ARRAY destinations
     */

    fun autoCorrectSuggestions(input: String, destinations: Array<String>): Array<String> {
        if (input.isEmpty() || destinations.isNullOrEmpty()) return emptyArray()
        return destinations.asSequence()
            .filter { it.length == input.length }
            .filterNotNull()
            .groupBy { getDiff(input, it) }
            .minByOrNull { it.key }?.value?.toTypedArray() ?: emptyArray()
    }

    private fun getDiff(input: String, curString: String): Int {
        val inputMap = getCountMap(input)
        val curMap = getCountMap(curString)
        var diff = 0
        for ((index, value) in inputMap.withIndex()) {
            if (curMap[index] != value) {
                diff++
            }
        }
        return diff
    }

    private fun getCountMap(input: String): IntArray {
        val inputMap = IntArray(26)
        input.forEach {
            if (!it.isLetter()) throw IllegalArgumentException("input contains illeagal char: $it")
            inputMap[it - 'a']++
        }
        return inputMap
    }
}

fun main() {
    Result.autoCorrectSuggestions("nalga", arrayOf(
        "bali",
        "malta",
        "palma",
        "paris",
    )
    ).print()
}