package com.blankj.google

/**
 *  Given on-call rotation schedule for multiple people by: their name, start time and end time of the rotation:
 *
 *      Abby 1 10
 *      Ben 5 7
 *      Carla 6 12
 *      David 15 17
 *
 *  Your goal is to return rotation table without overlapping periods representing who is on call during that time. Return "Start time", "End time" and list of on-call people:
 *
 *      1 5 Abby
 *      5 6 Abby, Ben
 *      6 7 Abby, Ben, Carla
 *      7 10 Abby, Carla
 *      10 12 Carla
 *      15 17 David
 */
data class Rotation(val name: String, val startTime: Int, val endTime: Int)

fun getOnCallRotationTable(rotations: List<Rotation>): List<Triple<Int, Int, List<String>>> {
    // Sort rotations by start time
    val orderedIntervals = rotations.fold(mutableListOf<Triple<Int, Int, String>>()) { acc, rotation ->
        acc.add(Triple(rotation.startTime, -1, rotation.name))
        acc.add(Triple(rotation.endTime, 1, rotation.name))
        acc
    }
    orderedIntervals.sortWith { i1, i2 ->
        if (i1.first != i2.first) {
            return@sortWith i1.first - i2.first
        }
        return@sortWith i1.second - i2.second
    }
    val curOnCall = mutableSetOf<String>()
    // Initialize result list
    val result = mutableListOf<Triple<Int, Int, List<String>>>()
    var startTime = -1
    for ((curTime, sign, name) in orderedIntervals) {
        if (startTime == -1) {
            curOnCall.add(name)
        } else if (curOnCall.isNotEmpty()) {
            result.add(Triple(startTime, curTime, curOnCall.toList()))
        }
        if (sign == -1) {
            curOnCall.add(name)
        } else {
            curOnCall.remove(name)
        }
        startTime = curTime
    }

    return result
}

fun main() {
    val rotations = listOf(
        Rotation("Abby", 1, 10),
        Rotation("Ben", 5, 7),
        Rotation("Carla", 6, 12),
        Rotation("David", 15, 17)
    )

    val rotationTable = getOnCallRotationTable(rotations)

    // Print the result
    for ((startTime, endTime, names) in rotationTable) {
        println("$startTime $endTime ${names.joinToString(", ")}")
    }
}
