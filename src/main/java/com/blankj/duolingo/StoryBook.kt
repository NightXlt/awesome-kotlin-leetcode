package com.blankj.duolingo

import com.blankj.ext.print

class StoryBook {
    var visited = mutableSetOf<String>()
    var res = mutableListOf<List<Int>>()
    var choice = mapOf<Int, List<Int>>()

    fun getAllPages(endings: Set<Int>, choice: List<List<Int>>): Any {
        if (endings.isEmpty()) error("endings set is empty!")
        val stack = ArrayDeque<Int>()
        this.choice = choice.associate {
            it[0] to it.takeLast(it.size - 1)
        }
        visited = mutableSetOf()
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
            if ("${start}_$next" in visited) continue
            visited.add("${start}_$next")
            dfs(next, stack, endings)
            stack.removeLast()
            visited.remove("${start}_$next")
        }
    }
}

fun main() {
    StoryBook().getAllPages(
        setOf(5, 10),
        listOf(
            listOf(3, 7, 9),
            listOf(9, 10, 8)
        )
    ).print()
}