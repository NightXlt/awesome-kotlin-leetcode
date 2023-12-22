package com.blankj.google

class ComplementSet {

    fun complementSet(list1: List<Int>, list2: List<Int>): Pair<List<Int>, List<Int>> {
        if (list1.size > list2.size) return complementSet(list2, list1)
        val freq = mutableMapOf<Int, Boolean>()
        list1.forEach {
            freq[it] = true
        }
        val intersectionSet = mutableListOf<Int>()
        val res2 = mutableListOf<Int>()
        for (num in list2) {
            if (num !in freq) {
                res2.add(num)
            } else {
                intersectionSet.add(num)
            }
        }
        return (list1 - intersectionSet.toSet()) to res2
    }
}


fun main() {
    println(
        ComplementSet().complementSet(
            listOf(1, 3, 2, 1, 2), listOf(5, 6, 7, 1, 2)
        )
    )
}