package com.blankj.hard._068

import com.blankj.ext.print

class Solution {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val res = mutableListOf<String>()
        var left: Int
        var right = 0
        while (true) {
            left = right
            var sumLength = 0
            while (right < words.size && sumLength + words[right].length + (right - left) <= maxWidth) {
                sumLength += words[right++].length
            }
            if (right == words.size) {
                val lastLine = join(words, left, words.size, " ")
                lastLine.append(" ".repeat(maxWidth - lastLine.length))
                res.add(lastLine.toString())
                return res
            }
            val numWords = right - left
            val numSpaces = maxWidth - sumLength
            if (numWords == 1) {
                res.add(buildString {
                    append(words[left])
                    append(" ".repeat(numSpaces))
                })
                continue
            }

            val avgSpaces = numSpaces / (numWords - 1)
            val countWithextraSpaces = numSpaces % (numWords - 1)
            res.add(buildString {
                append(join(words, left, left + countWithextraSpaces + 1, " ".repeat(avgSpaces + 1)))
                append(" ".repeat(avgSpaces))
                append(join(words, left + countWithextraSpaces + 1, right, " ".repeat(avgSpaces)))
            })
        }
    }

    // Join string from left to (right - 1)
    private fun join(words: Array<String>, left: Int, right: Int, separator: String): StringBuilder {
        val builder = StringBuilder(words[left])
        for (i in left + 1..<right) {
            builder.append(separator)
            builder.append(words[i])
        }
        return builder
    }
}

fun main() {
    Solution().fullJustify(
        arrayOf("This", "is", "an", "example", "of", "text", "justification."),
        16
    ).print()
    Solution().fullJustify(
        arrayOf("Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"),
        20
    ).print()
}