package com.blankj.hard._975

import java.util.*

class Solution {
    fun oddEvenJumps(arr: IntArray): Int {
        if (arr.size <= 1) return arr.size
        val even = BooleanArray(arr.size)
        val odd = BooleanArray(arr.size)
        odd[odd.lastIndex] = true
        even[even.lastIndex] = true
        val visited = TreeMap<Int, Int>()
        visited[arr.last()] = arr.lastIndex
        for (i in arr.lastIndex - 1 downTo 0) {
            if (arr[i] in visited) {
                odd[i] = even[visited.getValue(arr[i])]
                even[i] = odd[visited.getValue(arr[i])]
            } else {
                val lower = visited.lowerKey(arr[i])
                val higher = visited.higherKey(arr[i])
                if (lower != null) {
                    even[i] = odd[visited.getValue(lower)]
                }
                if (higher != null) {
                    odd[i] = even[visited.getValue(higher)]
                }
            }
            visited[arr[i]] = i
        }
        return odd.count { it }
    }
}