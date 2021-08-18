package com.blankj.afterpay.firse_round

import com.blankj.coding_interviews._004.print
import kotlin.math.min

class Solution {

    fun findPreviousStation(
        stations: Array<Array<String>>?,
        targetStation: String?
    ): List<String>? {
        if (stations == null || targetStation == null) {
            throw IllegalArgumentException("argument is null")
        }
        val map = mutableMapOf<String, MutableSet<String>>()
        for (i in stations.indices) {
            for (j in stations[i].indices) {
                if (j > 0) {
                    val curStation = stations[i][j]
                    val preStation = stations[i][j - 1]
                    val list = map.getOrDefault(curStation, mutableSetOf())
                    list.add(preStation)
                    map[curStation] = list
                }
            }
        }

        return map[targetStation]?.toList()
    }

    var minCount = Int.MAX_VALUE

    fun countStation(
        stations: Array<Array<String>>?,
        startStation: String?,
        endStation: String?
    ): Int {
        if (stations == null
            || startStation == null
            || endStation == null
        ) return -1
        val map = mutableMapOf<String, MutableSet<String>>()
        for (i in stations.indices) {
            for (j in stations[i].indices) {
                if (j < stations[i].lastIndex) {
                    val curStation = stations[i][j]
                    val nextStation = stations[i][j + 1]
                    val set = map[curStation] ?: mutableSetOf()
                    set.add(nextStation)
                    map[curStation] = set
                }
            }
        }
        dfs(map, startStation, endStation, 0)
        return minCount
    }

    private fun dfs(
        map: MutableMap<String, MutableSet<String>>,
        startStation: String,
        endStation: String,
        count: Int
    ) {
        val mutableSet = map[startStation] ?: return
        if (mutableSet.contains(endStation)) {
            minCount = min(count + 1, minCount)
            return
        }
        if (count > minCount) {
            return
        }
        for (station in mutableSet) {
            dfs(map, station, endStation, count + 1)
        }
    }
}

fun main() {
    /**
     * [
    ["Shanghai","Tokyo" , "New York" , "London" ,"Florence"],
    ["London" ,"Paris" , "Florence" ,"Singapore"],
    ["Tokyo" , "Hongkong" , "Melbourne", "Los Angeles"],
    ["Shanghai" , "Hongkong" , "New York", "Singapore"]
    ]
     */

    Solution().countStation(
        arrayOf(
            arrayOf("Shanghai","Tokyo" , "New York" , "London" ,"Florence"),
            arrayOf("London" ,"Paris" , "Florence" ,"Singapore"),
            arrayOf("Tokyo" , "Hongkong" , "Melbourne", "Los Angeles"),
            arrayOf("Shanghai" , "Hongkong" , "New York", "Singapore")
        ),
        startStation = "London",
        endStation = "New York"
    ).print()
}