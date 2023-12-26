package com.blankj.hard._2842

import java.util.*

class Solution {
    fun countKSubsequencesWithMaxBeauty(s: String, k: Int): Int {
        val count = IntArray(26)
        s.forEach { count[it - 'a']++ }
        val map = TreeMap<Int, Int>()
        count.filter { it != 0 }
            .forEach { map.merge(it, 1, Integer::sum) }
        var res = 1L
        var residual = k
        for (e in map.descendingMap().entries) {
            val (charFreqs, num) = e
            if (num >= residual) {
                return (res * pow(charFreqs, residual) % MOD * comb(num, residual) % MOD).toInt()
            }
            res = res * pow(charFreqs, num) % MOD
            residual -= num
        }
        return 0
    }

    private fun comb(num: Int, residual: Int): Long {
        var res = num
        var n = num - 1
        for (i in 2..residual) {
            res = res * n / i
            n--
        }
        return res % MOD
    }

    private fun pow(x: Int, n: Int): Long {
        var res = 1L
        var exp = n
        var base = x.toLong()
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = res * x % MOD
            }
            base = base * base % MOD
            exp /= 2
        }
        return res
    }

    companion object {
        const val MOD: Long = (1e9 + 7).toLong()
    }
}