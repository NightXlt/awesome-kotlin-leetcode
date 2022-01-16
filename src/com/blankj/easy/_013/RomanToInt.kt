package com.blankj.easy._013

import com.blankj.ext.print

class RomanToInt {
    fun romanToInt(s: String): Int {
        if (s.isEmpty()) return 0
        // With default used with getValue method could avoid throwing exception
        val map = hashMapOf(
                'I' to 1,
                'V' to 5,
                'X' to 10,
                'L' to 50,
                'C' to 100,
                'D' to 500,
                'M' to 1000
        ).withDefault { -1 }
        // get extension doesnt care default value.
        // use get value would get key from value and default
        var sum = map.getValue(s.last())
        for (i in s.length - 2 downTo 0) {
            val value = map.getValue(s[i])
            if (value < map.getValue(s[i + 1])) {
                sum -= value
            } else {
                sum += value
            }
        }
        return sum
    }
}

fun main() {
    RomanToInt().romanToInt("LVIII").print()
    RomanToInt().romanToInt("IX").print()
    RomanToInt().romanToInt("PPPPPP").print()
}