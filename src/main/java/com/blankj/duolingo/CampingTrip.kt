package com.blankj.duolingo

import com.blankj.ext.print

/**
 * Time Complexity: O(n + V + E)
 * Space Complexity: O(n + E + V)
 * Here, n represents the number of trails, V represents the number of vertices (attractions or stops), E represents the number of edges (trails)
 */
class CampingTrip {

    private var attractionsSet = mutableSetOf<String>()
    private var trailChoice = mutableMapOf<String, MutableMap<String, Int>>()

    private fun isValidInput(trails: List<Pair<String, String>>, attractions: MutableSet<String>): Boolean {
        var isStartValid = false
        var isEndValid = false
        for (trail in trails) {
            if (trail.first == START_POINT || trail.second == START_POINT) {
                isStartValid = true
            }
            if (trail.first == END_POINT || trail.second == END_POINT) {
                isEndValid = true
            }
            if (trail.first in attractions) {
                attractions.remove(trail.first)
            }
            if (trail.second in attractions) {
                attractions.remove(trail.second)
            }

        }
        return isStartValid && isEndValid && attractions.isEmpty()
    }

    fun hasPath(trails: List<Pair<String, String>>, attractions: List<String>): Boolean {
        if (trails.isEmpty()) return false
        if (!isValidInput(trails, attractions.toMutableSet())) return false
        attractionsSet = attractions.toMutableSet()
        trailChoice = mutableMapOf()
        for (trail in trails) {
            val destToCount = trailChoice.getOrPut(trail.first) { mutableMapOf() }
            destToCount.merge(trail.second, 1, Integer::sum)
            val destToCountReversed = trailChoice.getOrPut(trail.second) { mutableMapOf() }
            destToCountReversed.merge(trail.first, 1, Integer::sum)
        }
        val visited = mutableMapOf<String, MutableSet<String>>()
        return dfs(START_POINT, visited)
    }

    private fun dfs(cur: String, visited: MutableMap<String, MutableSet<String>>): Boolean {
        if (cur == END_POINT && attractionsSet.isEmpty()) {
            return true
        }

        if (cur !in trailChoice) {
            return false
        }
        for (nextStop in trailChoice.getValue(cur)) {
            var (dest, count) = nextStop
            if (count == 0 && (visited[cur]?.contains(dest) == true || visited[dest]?.contains(cur) == true)) continue
            count--
            updateCount(cur, dest, count)
            if (count == 0) {
                visited.getOrPut(cur) { mutableSetOf() }.add(dest)
            }
            val isRemoved = dest in attractionsSet
            if (isRemoved) {
                attractionsSet.remove(dest)
            }
            val res = dfs(dest, visited)
            if (res) {
                return true
            }
            if (isRemoved) {
                attractionsSet.add(dest)
            }
            if (count == 0) {
                visited.getValue(cur).remove(dest)
            }
            count++
            updateCount(cur, dest, count)
        }
        return false
    }

    private fun updateCount(cur: String, dest: String, count: Int) {
        trailChoice.getValue(cur)[dest] = count
        trailChoice.getValue(dest)[cur] = count
    }

    companion object {
        private const val START_POINT = "Parking Lot"
        private const val END_POINT = "Campsite"
    }
}

fun main() {
    val trails1 = listOf(
        "Beaver Dam" to "Frozen Ocean",
        "Beaver Dam" to "Frozen Ocean",
        "Parking Lot" to "Beaver Dam",
        "Parking Lot" to "Liberty Lake",
        "Beaver Dam" to "Campsite",
        "Eel Weir" to "Campsite",
        "Eel Weir" to "Campsite",
    )
    CampingTrip().hasPath(
        trails1,
        listOf("Frozen Ocean")
    ).print()
    CampingTrip().hasPath(
        trails1,
        listOf("Liberty Lake", "Beaver Dam")
    ).print()
    CampingTrip().hasPath(
        trails1,
        listOf("Eel Weir")
    ).print()

    val trails2 = listOf(
        "Mason's Cabin" to "Liberty Lake",
        "Parking Lot" to "Mill Falls",
        "Mason's Cabin" to "Jeremy's Bay",
        "Eel Weir" to "Hardwood Forest",
        "Outdoor Theater" to "Campsite",
        "Jeremy's Bay" to "Horseshoe Falls",
        "Mason's Cabin" to "Parking Lot",
        "Mason's Cabin" to "Liberty Lake",
        "Mill Falls" to "Horseshoe Falls",
        "Mill Falls" to "Eel Weir",
        "Hardwood Forest" to "Campsite",
        "Eel Weir" to "Outdoor Theater",
        "Liberty Lake" to "Mason's Cabin"
    )
    CampingTrip().hasPath(
        trails2,
        listOf("Jeremy's Bay", "Mason's Cabin", "Outdoor Theater")
    ).print()
    CampingTrip().hasPath(
        trails2,
        listOf("Outdoor Theater", "Eel Weir", "Hardwood Forest")
    ).print()
    CampingTrip().hasPath(
        trails2,
        listOf("Liberty Lake")
    ).print()
    CampingTrip().hasPath(
        trails2,
        listOf("Horseshoe Falls", "Eel Weir")
    ).print()
}