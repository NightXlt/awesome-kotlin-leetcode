package com.blankj.hard._273

import com.blankj.coding_interviews._004.print

class Solution {

    companion object {
        private val lessThanTwenty = arrayOf(
                "Zero", "One", "Two", "Three",
                "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Eleven",
                "Twelve", "Thirteen", "Fourteen",
                "Fifteen", "Sixteen", "Seventeen",
                "Eighteen", "Nineteen")

        private val Tens = arrayOf(
                "", "", "Twenty", "Thirty",
                "Forty", "Fifty", "Sixty",
                "Seventy", "Eighty", "Ninety")

        private val SEPARATOR = arrayOf(
                "",
                "Thousand",
                "Million",
                "Billion"
        )
    }

    fun numberToWords(num: Int): String {
        if (num < 20) return lessThanTwenty[num]
        var num = num
        var i = 0
        val result: StringBuilder = StringBuilder()
        while (num > 0) {
            val t = num % 1000
            if (t != 0) {
                result.insert(0, getNumToString(t) + SEPARATOR[i] + " ")
            }
            i++
            num /= 1000
        }
        return result.trim().toString()
    }

    private fun getNumToString(num: Int): String {
        return when {
            // zero branch match required; such as 90, return ninety " "， or else return ninety "  " (Two blanks
            num == 0 -> ""
            num < 20 -> lessThanTwenty[num] + " "
            num < 100 -> {
                Tens[num / 10] + " " + getNumToString(num % 10)
            }
            else -> {
                lessThanTwenty[num / 100] + " Hundred " + getNumToString(num % 100)
            }
        }
    }
}

fun main() {
    Solution().numberToWords(506).print()
    Solution().numberToWords(1234567).print()
    Solution().numberToWords(1000000).print()
    Solution().numberToWords(1000).print()
    Solution().numberToWords(1234).print()
    Solution().numberToWords(0).print()
    Solution().numberToWords(11).print()
    Solution().numberToWords(99).print()
    Solution().numberToWords(8).print()
}