package com.blankj.duolingo

import com.blankj.ext.print

class StoryBook {

    var visited = mutableMapOf<Int, MutableSet<Int>>()
    var res = mutableListOf<List<Int>>()
    private var choice = mapOf<Int, List<Int>>()

    fun getAllPages(endings: Set<Int>, choice: List<List<Int>>): Any {
        if (endings.isEmpty()) error("endings set is empty!")
        val stack = ArrayDeque<Int>()
        this.choice = choice.associate {
            it[0] to it.takeLast(it.size - 1)
        }
        visited = mutableMapOf()
        res = mutableListOf()
        dfs(1, stack, endings)
        if (res.isEmpty()) return -1
        val entry = res.flatten().groupBy { it }
            .maxBy { it.value.size }
        return entry.key to entry.value.size
    }

    private fun dfs(
        start: Int,
        stack: ArrayDeque<Int>,
        endings: Set<Int>,
    ) {
        stack.add(start)
        if (start in endings) {
            res.add(stack.toList())
            return
        }
        if (start !in choice) {
            dfs(start + 1, stack, endings)
            stack.removeLast()
            return
        }
        for (next in choice.getValue(start)) {
            if (next == stack.last()) continue
            val curChoiceVisited = visited.getOrPut(start) { mutableSetOf() }
            if (next in curChoiceVisited) continue
            visited.getValue(start).add(next)
            dfs(next, stack, endings)
            stack.removeLast()
            visited.getValue(start).remove(next)
        }
    }
}

fun main() {
    val start = System.currentTimeMillis()
    StoryBook().getAllPages(
        setOf(5, 10),
        listOf(
            listOf(3, 7, 9),
            listOf(9, 10, 8)
        )
    ).print()
    println(System.currentTimeMillis() - start)
}