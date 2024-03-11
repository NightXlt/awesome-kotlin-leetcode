package com.blankj.medium._2116

class Solution {
    fun canBeValid(s: String, locked: String): Boolean {
        if (s.length != locked.length || s.length % 2 == 1) return false
        var left = 0
        var right = 0
        for (i in s.indices) {
            if (s[i] == '(' || locked[i] == '0') {
                left++
            } else {
                right++
            }
            if (right > left) return false
        }
        left = 0
        right = 0
        for (i in s.indices.reversed()) {
            if (s[i] == ')' || locked[i] == '0') {
                right++
            } else {
                left++
            }
            if (left > right) return false
        }
        return true
    }
}