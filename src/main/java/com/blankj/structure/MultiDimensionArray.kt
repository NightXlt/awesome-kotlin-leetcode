package com.blankj.structure

object MultiDimensionArray {
    public fun createTestData(data: String): Array<IntArray> {
        val rows = data.trim().substring(2, data.length - 2) // Remove outer brackets
        val rowStrings = rows.split("],[").toTypedArray() // Split by "],["
        val result = Array(rowStrings.size) {
            rowStrings[it].split(",").map { it.toInt() }.toIntArray() // Convert to integers
        }
        return result
    }
}