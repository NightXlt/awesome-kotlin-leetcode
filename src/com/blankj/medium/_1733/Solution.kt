package com.blankj.medium._1733


class Solution {
    private fun hasCommon(languages: Array<IntArray>, friend1: Int, friend2: Int): Boolean {
        val x = languages[friend1 - 1]
        val y = languages[friend2 - 1]
        return x.any { y.contains(it) }
    }

    fun minimumTeachings(n: Int, languages: Array<IntArray>, friendships: Array<IntArray>): Int {
        val mostLanguages = IntArray(n)
        val noConnects = mutableSetOf<Int>()
        for ((friend1, friend2) in friendships) {
            if (!hasCommon(languages, friend1, friend2)) {
                noConnects.add(friend1)
                noConnects.add(friend2)
            }
        }
        for (noConnectMan in noConnects) {
            languages[noConnectMan].forEach {
                mostLanguages[it]++
            }
        }
        val max = mostLanguages.maxOrNull() ?: 0
        return noConnects.size - max
    }
}