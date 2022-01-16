package com.blankj.medium._017

import com.blankj.ext.print

class LetterCombinations {
    fun letterCombinations(digits: String): List<String> {
        val result = mutableListOf<String>()
        if (digits.isEmpty()) return result
        val len: Int = digits.length
        val letters = arrayOf(
                "abc", "def", "ghi",
                "jkl", "mno", "pqrs",
                "tuv", "wxyz"
        )
        val lettersOfDigits = Array(len) {
            return@Array letters[digits[it].toInt() - '0'.toInt() - 2]
        }
        dfs(result, 0, lettersOfDigits, "")
        return result
    }

    private fun dfs(
            result: MutableList<String>,
            startIndex: Int,
            lettersOfDigits: Array<String>,
            letterComb: String
    ) {
        val curLetterComb = lettersOfDigits[startIndex]
        for (i in curLetterComb.indices) {
            if (startIndex == lettersOfDigits.lastIndex) {
                result.add(letterComb + curLetterComb[i])
            } else {
                dfs(
                        result,
                        startIndex + 1,
                        lettersOfDigits,
                        letterComb + curLetterComb[i]
                )
            }
        }
    }
}

fun main() {
    LetterCombinations().letterCombinations("23").print()
    LetterCombinations().letterCombinations("").print()
    LetterCombinations().letterCombinations("2").print()
}