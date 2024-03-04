package com.blankj.duolingo

// Greedy
class MinimumTeachings {
    private fun hasCommon(languages: Array<IntArray>, friend1: Int, friend2: Int): Boolean {
        val x = languages[friend1 - 1]
        val y = languages[friend2 - 1]
        // T: O(n^2) 因为 y 是 array 因此需要顺序遍历进行查找
        return x.any { y.contains(it) }
    }

    fun minimumTeachings(n: Int, languages: Array<IntArray>, friendships: Array<IntArray>): Int {
        val mostLanguages = IntArray(n)
        val noConnects = mutableSetOf<Int>()
        // T: O(f*n)
        for ((friend1, friend2) in friendships) {
            if (!hasCommon(languages, friend1, friend2)) {
                noConnects.add(friend1)
                noConnects.add(friend2)
            }
        }
        // O(m*n)
        for (noConnectMan in noConnects) {
            languages[noConnectMan].forEach {
                mostLanguages[it]++
            }
        }
        // T: O(n)
        val max = mostLanguages.maxOrNull() ?: 0
        return noConnects.size - max
    }
}